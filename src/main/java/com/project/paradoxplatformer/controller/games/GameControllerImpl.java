package com.project.paradoxplatformer.controller.games;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.controller.event.EventManager;
import com.project.paradoxplatformer.controller.event.GameEventType;
import com.project.paradoxplatformer.controller.gameloop.GameLoopFactoryImpl;
import com.project.paradoxplatformer.controller.gameloop.ObservableLoopManager;
import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.controller.input.api.KeyInputer;
import com.project.paradoxplatformer.model.GameModelData;
import com.project.paradoxplatformer.model.effect.EffectHandlerFactoryImpl;
import com.project.paradoxplatformer.model.effect.api.Level;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.entity.ReadOnlyMutableObjectWrapper;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.entity.dynamics.abstracts.AbstractControllableObject;
import com.project.paradoxplatformer.model.entity.dynamics.behavior.FlappyJump;
import com.project.paradoxplatformer.model.entity.dynamics.behavior.PlatformJump;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.utils.collision.CollisionManager;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.game.GameView;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;
import com.project.paradoxplatformer.view.graphics.ReadOnlyGraphicDecorator;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.view.legacy.ViewLegacy;
import com.project.paradoxplatformer.view.manager.ViewNavigator;
import com.project.paradoxplatformer.utils.InvalidResourceException;

/**
 * An implementation of a basic Game Controller.
 * 
 * @param <C> type of view component
 */
public final class GameControllerImpl<C> implements GameController<C>, GameEventListener {

    private final GameModelData gameModel;
    private Map<MutableObject, ReadOnlyGraphicDecorator<C>> gamePairs;
    private final GameView<C> gameView;
    private final Function<GraphicAdapter<C>, Coord2D> position;
    private final Function<GraphicAdapter<C>, Dimension> dimension;

    private CollisionManager collisionManager;
    private final ObjectRemover<C> objectRemover;

    private final Random rand = new Random();
    private final EventManager<GameEventType, PageIdentifier> eventManager;
    private ObservableLoopManager gameManager;
    private final String modelID;

    /**
     * A generic constuctor of a gamecontroller.
     * 
     * @param model model type
     * @param view  view type
     */
    public GameControllerImpl(final GameModelData model, final GameView<C> view, String id) {
        this.gameModel = model;
        this.gameView = view;
        this.gamePairs = new HashMap<>();
        this.position = GraphicAdapter::relativePosition;
        this.dimension = GraphicAdapter::dimension;
        this.collisionManager = new CollisionManager(new EffectHandlerFactoryImpl().defaultEffectHandler());
        this.modelID = id;

        this.eventManager = EventManager.getInstance();
        this.objectRemover = new ObjectRemover<>(model, view);

        eventManager.subscribe(GameEventType.UPDATE_HANDLER, this::updateHandler);
        eventManager.subscribe(GameEventType.STOP_VIEW, this::handleStopView);
        eventManager.subscribe(GameEventType.REMOVE_OBJECT, this::handleRemoveObject);
        eventManager.subscribe(GameEventType.TRIGGER_EFFECT, this::handleTriggerEffect);
    }

    @Override
    public void loadModel() {
        gameModel.init();
        System.out.println("Game Model is loaded.");
    }

    @Override
    public void syncView() {
        gameView.init();
        this.sync(true);

        System.out.println("Game View is loaded.");
    }

    private void handleStopView(final PageIdentifier id, final Level param) {
        System.out.println("STOPPING VIEW BEFORE RECREATE IT.");
        this.gameManager.stop();
    }

    private void handleTriggerEffect(final PageIdentifier id, final Obstacle param) {
        System.out.println(param + " FROM GAME CONTROLLER.");
    }

    private void handleRemoveObject(final PageIdentifier id, Optional<? extends CollidableGameObject> object) {
        objectRemover.handleRemoveObject(id, object);
    }

    public <T> void removeGameObjects() {
        objectRemover.removeGameObjects(gamePairs);
    }

    private void updateHandler(final PageIdentifier id, final Level param) {
        System.out.println("SWITCH COLLISION MANAGER'S HANDLER.");
        this.collisionManager = new CollisionManager(new EffectHandlerFactoryImpl().getEffectHandlerForLevel(param));

    }

    private void sync(final boolean firstTime) {
        gamePairs = this.gameView.getUnmodifiableControls()
                .stream()
                .map(g -> this.join(new ReadOnlyGraphicDecorator<>(g), this.gameModel.getWorld(), firstTime))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    private Pair<MutableObject, ReadOnlyGraphicDecorator<C>> join(
            final ReadOnlyGraphicDecorator<C> g,
            final World world,
            final boolean firstTime) {

        final Set<MutableObject> str = new LinkedHashSet<>(world.gameObjects());

        Pair<MutableObject, ReadOnlyGraphicDecorator<C>> pair = str.stream()
                .filter(m -> this.joinPredicate(m, g, firstTime))
                .map(m -> Pair.of(m, g))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Failed to pair object and graphic\nCause: "
                                + "\nGraphic: " + dimension.apply(g)
                                + "\nGraphic: " + position.apply(g)));

        // Imposta il listener se l'oggetto è il palyer
        if (pair.getKey() instanceof AbstractControllableObject) {
            ((AbstractControllableObject) pair.getKey()).setGameEventListener(this);
        }

        if (firstTime) {
            this.assignKey(pair.getKey(), pair.getValue());
        }

        return pair;
    }

    private void assignKey(MutableObject mutableObject, ReadOnlyGraphicDecorator<C> graphicDecorator) {
        final int key = rand.nextInt();
        mutableObject.setKey(key);
        graphicDecorator.setKey(key);
    }

    private boolean joinPredicate(final MutableObject mutableObject, final GraphicAdapter<C> gComponent,
            final boolean firstTime) {
        return firstTime ? mutableObject.getDimension().equals(dimension.apply(gComponent))
                && mutableObject.getPosition().equals(position.apply(gComponent))
                : mutableObject.getID() == gComponent.getID();
    }

    @Override
    public <K> void startGame(final InputController<ControllableObject> ic, final KeyInputer<K> inputer, String type) {
        this.setupGameMode(gameModel.getWorld().player(), type);
        this.gameManager = new GameLoopFactoryImpl(dt -> {
            ic.checkPool(
                    inputer.getKeyAssetter(),
                    gameModel.getWorld().player(),
                    ControllableObject::stop);
            this.update(dt);
        }).animationLoop();
        this.gameManager.start();

    }

    private void setupGameMode(ControllableObject player, String type) {
        if ("flappy".equalsIgnoreCase(type)) {
            player.setJumpBehavior(new FlappyJump());
        } else {
            player.setJumpBehavior(new PlatformJump());
        }
    }

    /**
     * 
     * 
     * @param dt
     */
    public void update(final long dt) {
        if (Objects.nonNull(gamePairs)) {

            gamePairs.forEach((m, g) -> m.updateState(dt));
            CollidableGameObject player = this.gameModel.getWorld().player();

            this.collisionManager.handleCollisions(gamePairs.keySet(),
                    player);

            this.readOnlyPairs(gamePairs).forEach(this.gameView::updateControlState);

            removeGameObjects();

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

    private void resync() {
        this.sync(false);
    }

    @Override
    public void onPlayerDeath() {
        // Ricarica il livello
        try {
            // this.restartGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restartGame() {
        this.gameManager.stop();
        System.out.println("RESTART");

        try {
            ViewLegacy.javaFxFactory().mainAppManager().get().switchPage(PageIdentifier.GAME).create(modelID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exitGame() {
        this.gameManager.stop();
        System.out.println("EXITED");
        try {
            ViewNavigator.getInstance().goToMenu();
        } catch (InvalidResourceException e) {
            e.printStackTrace();
        }
    }

}
