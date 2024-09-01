package com.project.paradoxplatformer.controller.games;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.controller.gameloop.GameLoopFactoryImpl;
import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.controller.input.api.KeyInputer;
import com.project.paradoxplatformer.model.GameModelData;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.entity.dynamics.abstracts.AbstractControllableObject;
import com.project.paradoxplatformer.model.entity.dynamics.behavior.FlappyJump;
import com.project.paradoxplatformer.model.entity.dynamics.behavior.PlatformJump;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.game.GameView;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

/**
 * An implementation of a basic Game Controller.
 * 
 * @param <C> type of view component
 */
public final class GameControllerImpl<C> implements GameController<C>, GameEventListener {

    private final GameModelData gameModel;
    private Map<MutableObject, GraphicAdapter<C>> gamePair;
    private final GameView<C> gameView;
    private final Function<GraphicAdapter<C>, Coord2D> position;
    private final Function<GraphicAdapter<C>, Dimension> dimension;

    /**
     * A generic constuctor of a gamecontroller.
     * 
     * @param model model type
     * @param view  view type
     */
    public GameControllerImpl(final GameModelData model, final GameView<C> view) {
        this.gameModel = model;
        this.gameView = view;
        this.gamePair = new HashMap<>();
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
        gamePair = this.gameView.getUnmodifiableControls()
                .stream()
                .map(g -> this.join(g, this.gameModel.getWorld()))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

        System.out.println("Game View is loaded.");
    }

    private Pair<MutableObject, GraphicAdapter<C>> join(final GraphicAdapter<C> g, final World world) {
        final Set<MutableObject> str = Stream.concat(Stream.concat(this.gameModel.getWorld().obstacles().stream(),
                Stream.of(this.gameModel.getWorld().player())), this.gameModel.getWorld().triggers().stream())
                .collect(Collectors.toSet());
    
        Pair<MutableObject, GraphicAdapter<C>> pair = str.stream()
                    .filter(m -> this.joinPredicate(m, g))
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
    
        return pair;
    }
    

    private boolean joinPredicate(final MutableObject obstacle1, final GraphicAdapter<C> gComponent) {

        return obstacle1.getDimension().equals(dimension.apply(gComponent))
                && obstacle1.getPosition().equals(position.apply(gComponent));
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
        if (Objects.nonNull(gamePair)) {
            gamePair.forEach((m, g) -> m.updateState(dt));
            this.gameModel.getWorld().getCollisionManager().detectCollisions(gamePair.keySet(),
                    this.gameModel.getWorld().player());
            gamePair.forEach(this.gameView::updateEnitityState);
        }
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
