package com.project.paradoxplatformer.controller.games;

import com.google.common.collect.Sets;
import com.project.paradoxplatformer.controller.gameloop.GameLoopFactoryImpl;
import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.controller.input.api.KeyInputer;
import com.project.paradoxplatformer.model.GameModelData;
import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.entity.dynamics.behavior.FlappyJump;
import com.project.paradoxplatformer.model.entity.dynamics.behavior.PlatformJump;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.utils.collision.CollisionManager;
import com.project.paradoxplatformer.utils.collision.CollisionObserver;
import com.project.paradoxplatformer.utils.effect.EffectHandler;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.game.GameView;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;
import com.project.paradoxplatformer.view.graphics.ReadOnlyGraphicDecorator;

import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.model.entity.dynamics.abstracts.AbstractControllableObject;

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
    private final CollisionObserver collisionObserver = new CollisionObserver();
    private final Random rand = new Random();

    /**
     * A generic constuctor of a gamecontroller.
     * 
     * @param model model type
     * @param view  view type
     */
    public GameControllerImpl(final GameModelData model, final GameView<C> view) {
        this.gameModel = model;
        this.gameView = view;
        this.gamePairs = new HashMap<>();
        this.position = GraphicAdapter::relativePosition;
        this.dimension = GraphicAdapter::dimension;
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

    private void sync(final boolean firstTime) {
        gamePairs = this.gameView.getUnmodifiableControls()
                .stream()
                .map(g -> this.join(new ReadOnlyGraphicDecorator<>(g), this.gameModel.getWorld(), firstTime))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    private Pair<MutableObject, ReadOnlyGraphicDecorator<C>> join(
        final ReadOnlyGraphicDecorator<C> g, 
        final World world, 
        final boolean firstTime
        ) {

        final Set<MutableObject> str = Sets.union(new LinkedHashSet<>(world.gameObjects()), Set.of(world.player()));
    
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

        if(firstTime) {
            this.assignKey(pair.getKey(), pair.getValue());
        }

        return pair;
    }

    private void assignKey(MutableObject mutableObject, ReadOnlyGraphicDecorator<C> graphicDecorator) {
        final int key = rand.nextInt();
        mutableObject.setKey(key);
        graphicDecorator.setKey(key);
    }

    private boolean joinPredicate(final MutableObject mutableObject, final GraphicAdapter<C> gComponent, final boolean firstTime) {
        return firstTime ? mutableObject.getDimension().equals(dimension.apply(gComponent))
                && mutableObject.getPosition().equals(position.apply(gComponent))
                : mutableObject.getKey() == gComponent.getKey();
    }

    @Override
    public <K> void startGame(final InputController<ControllableObject> ic, final KeyInputer<K> inputer, String type) {
        this.setupGameMode(gameModel.getWorld().player(), type);
        new GameLoopFactoryImpl(dt -> {
            ic.checkPool(
                    inputer.getKeyAssetter(),
                    gameModel.getWorld().player(),
                    ControllableObject::stop
                );
            this.update(dt);
        })
                .animationLoop()
                .start();
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

            CollisionManager collisionManager = this.gameModel.getWorld().getCollisionManager();
            EffectHandler effectHandler = collisionManager.getEffectHandler();
            CollidableGameObject player = this.gameModel.getWorld().player();

            Set<CollidableGameObject> detectedCollisions = collisionManager.detectCollisions(gamePairs.keySet(), player);

            collisionObserver.observeCollisions(detectedCollisions,
                    (obj, type) -> System.out.println("Started colliding : " + obj.getClass().getSimpleName()),
                    (obj, type) -> effectHandler.reset(obj, type));

            gamePairs.forEach(this.gameView::updateEnitityState); 

            this.resync();
            
        }
    }

    private void resync() {
        this.sync(false);
    }

    public void restartLevel() {
        // Reinizzializza il modello del gioco
        this.gameModel.init();

        // Resetta la vista in modo che corrisponda al nuovo stato del modello
        this.syncView();
    }

    @Override
    public void onPlayerDeath() {
        // Ricarica il livello
        try {
            // this.restartLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
