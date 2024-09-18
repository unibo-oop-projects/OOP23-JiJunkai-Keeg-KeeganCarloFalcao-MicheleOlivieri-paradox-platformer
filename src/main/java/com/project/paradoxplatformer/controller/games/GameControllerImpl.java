package com.project.paradoxplatformer.controller.games;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.controller.gameloop.GameLoopFactoryImpl;
import com.project.paradoxplatformer.controller.gameloop.ObservableLoopManager;
import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.controller.input.api.KeyInputer;
import com.project.paradoxplatformer.model.GameModelData;
import com.project.paradoxplatformer.model.effect.impl.EffectHandlerFactoryImpl;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.entity.ReadOnlyMutableObjectWrapper;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.utils.collision.CollisionManager;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.endgame.DeathConditionsFactoryImpl;
import com.project.paradoxplatformer.utils.endgame.EndGameManager;
import com.project.paradoxplatformer.utils.endgame.EndGameManagerImpl;
import com.project.paradoxplatformer.utils.endgame.VictoryConditionsFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.game.GameView;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;
import com.project.paradoxplatformer.view.graphics.ReadOnlyGraphicDecorator;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.view.legacy.ViewFramework;
import com.project.paradoxplatformer.view.manager.ViewNavigator;

/**
 * An implementation of a basic Game Controller.
 * 
 * @param <C> type of view component
 */
public final class GameControllerImpl<C> implements GameController<C>, GameControllerEventListener {

    private final GameModelData gameModel;
    private Map<MutableObject, ReadOnlyGraphicDecorator<C>> gamePairs;
    private final GameView<C> gameView;
    private final Function<GraphicAdapter<C>, Coord2D> position;
    private final Function<GraphicAdapter<C>, Dimension> dimension;

    private final CollisionManager collisionManager;
    private final ObjectRemover<C> objectRemover;
    private final EndGameManager endGameManager;

    @SuppressWarnings("unused")
    private final GameControllerEventSubscriber eventSubscriber;

    private ObservableLoopManager gameManager;
    private final Level currentLevel;

    /**
     * A generic constuctor of a gamecontroller.
     * 
     * @param model model type
     * @param view  view type
     * @param level the level id (json file for the level)
     */
    public GameControllerImpl(final GameModelData model, final GameView<C> view, final Level level) {
        this.gameModel = model;
        this.gameView = view;
        this.gamePairs = new HashMap<>();
        this.position = GraphicAdapter::relativePosition;
        this.dimension = GraphicAdapter::dimension;
        this.collisionManager = new CollisionManager(new EffectHandlerFactoryImpl().getEffectHandlerForLevel(level));
        this.currentLevel = level;
        this.endGameManager = new EndGameManagerImpl(this.currentLevel);

        this.eventSubscriber = new GameControllerEventSubscriber(this);
        this.objectRemover = new ObjectRemover<>(model, view);

        System.out.println("Current level: " + level);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadModel() {
        gameModel.init();
        System.out.println("Game Model is loaded.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void syncView() {
        gameView.init();
        this.sync();

        System.out.println("Game View is loaded.");
    }

    private void sync() {
        gamePairs = this.gameView.getUnmodifiableControls()
                .stream()
                .map(g -> this.join(new ReadOnlyGraphicDecorator<>(g), this.gameModel.getWorld()))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    private Pair<MutableObject, ReadOnlyGraphicDecorator<C>> join(
            final ReadOnlyGraphicDecorator<C> g,
            final World world) {

        final Set<MutableObject> str = new LinkedHashSet<>(world.gameObjects());

        Pair<MutableObject, ReadOnlyGraphicDecorator<C>> pair = str.stream()
                .filter(m -> this.joinPredicate(m, g))
                .map(m -> Pair.of(m, g))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        """
                                Failed to pair object and graphic
                                Cause:
                                Graphic: """ + dimension.apply(g)
                                + "\nGraphic: " + position.apply(g)));
        return pair;
    }

    private boolean joinPredicate(final MutableObject mutableObject, final GraphicAdapter<C> gComponent) {
        return mutableObject.getID() == gComponent.getID();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <K> void startGame(final InputController<ControllableObject> ic, final KeyInputer<K> inputer, final String type) {
        this.endGameManager.setVictoryHandler(new VictoryConditionsFactoryImpl()
                .createConditionsForLevel(this.currentLevel, this.gameModel.getWorld().player()));
        this.endGameManager.setDeathHandler(new DeathConditionsFactoryImpl().createConditionsForLevel(this.currentLevel,
                this.gameModel.getWorld().player()));
        this.gameManager = new GameLoopFactoryImpl(dt -> {
            ic.checkPool(
                    inputer.getKeyAssetter(),
                    gameModel.getWorld().player(),
                    ControllableObject::stop);
            this.update(dt);
        }).animationLoop();
        this.gameManager.start();

    }

    /**
     * .
     * 
     * @param dt
     */
    public void update(final long dt) {
        if (Objects.nonNull(gamePairs)) {
            gamePairs.forEach((m, g) -> m.updateState(dt));
            CollidableGameObject player = this.gameModel.getWorld().player();

            this.collisionManager.handleCollisions(gamePairs.keySet(),
                    player);

            this.endGameManager.checkForDeath();
            this.endGameManager.checkForVictory();

            this.readOnlyPairs(gamePairs).forEach(this.gameView::updateControlState);

            this.removeGameObjects();

        }
    }

    private Map<ReadOnlyMutableObjectWrapper, ReadOnlyGraphicDecorator<C>> readOnlyPairs(
            final Map<MutableObject, ReadOnlyGraphicDecorator<C>> pairs) {
        return pairs.entrySet().stream()
                .map(p -> Pair.of(
                        new ReadOnlyMutableObjectWrapper(p.getKey()),
                        p.getValue()))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void restartGame() {
        this.gameManager.stop();
        System.out.println("RESTART");

        try {
            ViewFramework.javaFxFactory().mainAppManager().get().switchPage(PageIdentifier.GAME).create(currentLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void exitGame() {
        this.gameManager.stop();
        System.out.println("EXITED");
        ViewNavigator.getInstance().goToMenu();
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void handleStopView(final PageIdentifier id, final Level param) {
        System.out.println("STOPPING VIEW BEFORE RECREATE IT.");
        this.gameManager.stop();
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void handleRemoveObject(final PageIdentifier id, final Optional<? extends CollidableGameObject> object) {
        objectRemover.handleRemoveObject(id, object);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void handleTriggerEffect(final PageIdentifier id, final Obstacle param) {
        System.out.println(param + " TRIGGERED FROM GAME CONTROLLER.");
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public void handleVictory(final PageIdentifier id) {
        this.endGameManager.setVictoryHandler(new VictoryConditionsFactoryImpl()
                .createConditionsForLevel(this.currentLevel, this.gameModel.getWorld().player()));
    }

    private <T> void removeGameObjects() {
        objectRemover.removeGameObjects(gamePairs);
    }

}
