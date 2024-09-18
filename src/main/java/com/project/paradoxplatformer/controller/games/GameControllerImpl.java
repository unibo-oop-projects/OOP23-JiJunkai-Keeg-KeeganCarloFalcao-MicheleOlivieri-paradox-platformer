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
import com.project.paradoxplatformer.model.entity.dynamics.behavior.FlappyJump;
import com.project.paradoxplatformer.model.entity.dynamics.behavior.PlatformJump;
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
import com.project.paradoxplatformer.view.manager.ViewNavigator;

/**
 * Provides an implementation of the {@code GameController} interface, managing the 
 * interaction between the game model, view, and game loop. This class coordinates
 * the gameplay by updating the game state, handling user input, and managing game
 * events such as victory or game-over conditions.
 * 
 * @param <C> the type of view component managed by this controller
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
    private final GameControllerEventSubscriber eventSubscriber;

    private ObservableLoopManager gameManager;
    private final Level currentLevel;

    /**
     * Constructs a new {@code GameControllerImpl} instance with the specified model, view, and level.
     * 
     * @param model the game model data used to manage the state of the game
     * @param view  the game view used to render and interact with the game
     * @param level the current level being played in the game
     */
    public GameControllerImpl(final GameModelData model, final GameView<C> view, final Level level) {
        this.gameModel = model;
        this.gameView = Optional.of(view).orElseThrow(() -> new IllegalArgumentException("View cannot be null"));
        this.gamePairs = new HashMap<>();
        this.position = GraphicAdapter::relativePosition;
        this.dimension = GraphicAdapter::dimension;
        this.collisionManager = new CollisionManager(new EffectHandlerFactoryImpl().getEffectHandlerForLevel(level));
        this.currentLevel = level;
        this.endGameManager = new EndGameManagerImpl(this.currentLevel);

        this.eventSubscriber = new GameControllerEventSubscriber(this);
        this.eventSubscriber.subscribeToEvents();

        this.objectRemover = new ObjectRemover<>(model, view);
    }

    /**
     * Initializes the game model, setting it up for gameplay.
     */
    @Override
    public void loadModel() {
        gameModel.init();
    }

    /**
     * Initializes and synchronizes the game view with the current game state.
     */
    @Override
    public void syncView() {
        gameView.init();
        this.sync();
    }

    /**
     * Removes game objects from both the model and the view.
     * 
     * @param <T> the type of the objects to be removed
     */
    public <T> void removeGameObjects() {
        objectRemover.removeGameObjects(gamePairs);
    }

    /**
     * Synchronizes the game view with the game model by pairing each graphic component
     * with its corresponding mutable object in the world.
     */
    private void sync() {
        gamePairs = this.gameView.getUnmodifiableControls()
                .stream()
                .map(g -> this.join(new ReadOnlyGraphicDecorator<>(g), this.gameModel.getWorld()))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    /**
     * Joins a graphical component with its corresponding mutable object in the world.
     * 
     * @param g     the graphic component to join
     * @param world the game world containing the objects to pair with the graphic
     * @return a pair of the matched {@code MutableObject} and {@code ReadOnlyGraphicDecorator}
     * @throws IllegalArgumentException if no matching object is found for the given graphic
     */
    private Pair<MutableObject, ReadOnlyGraphicDecorator<C>> join(
            final ReadOnlyGraphicDecorator<C> g,
            final World world) {

        final Set<MutableObject> objects = new LinkedHashSet<>(world.gameObjects());

        return objects.stream()
                .filter(m -> this.joinPredicate(m, g))
                .map(m -> Pair.of(m, g))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Failed to pair object and graphic. Cause: Graphic: "
                                + dimension.apply(g) + "\nGraphic: " + position.apply(g)));
    }

    /**
     * Determines whether a mutable object and a graphic component should be joined based on their IDs.
     * 
     * @param mutableObject the mutable object to test
     * @param gComponent    the graphic component to test
     * @return true if the IDs of the mutable object and the graphic component match
     */
    private boolean joinPredicate(final MutableObject mutableObject, final GraphicAdapter<C> gComponent) {
        return mutableObject.getID() == gComponent.getID();
    }

    /**
     * Starts the game by setting up the player's controls and initiating the game loop.
     * 
     * @param <K>     the type of the key input
     * @param ic      the input controller for the player
     * @param inputer the key input handler
     * @param type    the game mode type (e.g., "flappy" or "platform")
     */
    @Override
    public <K> void startGame(
            final InputController<ControllableObject> ic, 
            final KeyInputer<K> inputer,
            final String type
        ) {
        this.setupGameMode(gameModel.getWorld().player(), type);
        this.endGameManager.setVictoryHandler(new VictoryConditionsFactoryImpl()
                .createConditionsForLevel(this.currentLevel, this.gameModel.getWorld().player()));
        this.endGameManager.setDeathHandler(new DeathConditionsFactoryImpl().createConditionsForLevel(this.currentLevel,
                this.gameModel.getWorld().player()));

        this.gameManager = new GameLoopFactoryImpl(dt -> {
            //main game loop
            ic.checkPool(
                inputer.getKeyAssetter(),
                gameModel.getWorld().player(),
                ControllableObject::stop
            );
            this.update(dt);
        }).animationLoop();

        this.gameManager.start();
    }

    /**
     * Configures the game mode by setting the player's jump behavior based on the provided type.
     * 
     * @param player the controllable player object
     * @param type   the game mode type (e.g., "flappy" or "platform")
     */
    private void setupGameMode(final ControllableObject player, final String type) {
        if ("flappy".equalsIgnoreCase(type)) {
            player.setJumpBehavior(Optional.of(new FlappyJump()));
        } else {
            player.setJumpBehavior(Optional.of(new PlatformJump()));
        }
    }

    /**
     * Updates the game state by processing object updates, handling collisions, and checking end-game conditions.
     * 
     * @param dt the time delta since the last update
     */
    public void update(final long dt) {
        if (Objects.nonNull(gamePairs)) {
            gamePairs.forEach((m, g) -> m.updateState(dt));
            CollidableGameObject player = this.gameModel.getWorld().player();

            this.collisionManager.handleCollisions(gamePairs.keySet(), player);

            this.endGameManager.checkForDeath();
            this.endGameManager.checkForVictory();

            this.readOnlyPairs(gamePairs).forEach(this.gameView::updateControlState);

            removeGameObjects();
        }
    }

    /**
     * Converts the mutable object and graphic pairs to read-only pairs.
     * 
     * @param pairs the map of mutable objects and their corresponding graphic
     *              decorators
     * @return a map of read-only wrappers of mutable objects and their
     *         corresponding graphic decorators
     */
    private Map<ReadOnlyMutableObjectWrapper, ReadOnlyGraphicDecorator<C>> readOnlyPairs(
            final Map<MutableObject, ReadOnlyGraphicDecorator<C>> pairs) {
        return pairs.entrySet().stream()
                .map(p -> Pair.of(
                        new ReadOnlyMutableObjectWrapper(p.getKey()),
                        p.getValue()))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    /**
     * Restarts the game by stopping the current game loop and recreating the game
     * view.
     */
    @Override
    public void restartGame() {
        this.gameManager.stop();
//        System.out.println("RESTART");

        try {
            ViewNavigator.getInstance().openView(PageIdentifier.GAME, currentLevel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exits the game by stopping the game loop and navigating back to the main
     * menu.
     */
    @Override
    public void exitGame() {
        this.gameManager.stop();
//        System.out.println("EXITED");
        ViewNavigator.getInstance().goToMenu();
    }

    /**
     * Handles the stop view event by stopping the game loop before recreating the
     * view.
     * 
     * @param id    the page identifier
     * @param param the level parameter
     */
    @Override
    public void handleStopView(final PageIdentifier id, final Level param) {
//        System.out.println("STOPPING VIEW BEFORE RECREATE IT.");
        this.gameManager.stop();
    }

    /**
     * Handles the removal of an object from the game world.
     * 
     * @param id     the page identifier
     * @param object the optional collidable game object to remove
     */
    @Override
    public void handleRemoveObject(final PageIdentifier id, final Optional<? extends CollidableGameObject> object) {
        objectRemover.handleRemoveObject(id, object);
    }

    /**
     * Handles the trigger effect event, logging the triggered obstacle.
     * 
     * @param id    the page identifier
     * @param param the obstacle parameter
     */
    @Override
    public void handleTriggerEffect(final PageIdentifier id, final Obstacle param) {
//        System.out.println(param + " TRIGGERED FROM GAME CONTROLLER.");
    }

    /**
     * Handles the victory event by setting up the victory conditions for the
     * current level.
     * 
     * @param id the page identifier
     */
    @Override
    public void handleVictory(final PageIdentifier id) {
        this.endGameManager.setVictoryHandler(new VictoryConditionsFactoryImpl()
                .createConditionsForLevel(this.currentLevel, this.gameModel.getWorld().player()));
    }

}
