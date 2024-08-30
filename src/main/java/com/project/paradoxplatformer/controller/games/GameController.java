package com.project.paradoxplatformer.controller.games;

import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.controller.input.api.KeyInputer;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;

/**
 * {@code GameController} loads the game model and then syncs to the view, finally it starts the game, 
 * they MUST be called sequentally.
 * Built this way to give a clear separation of concerns and a much coupled implementation.
 * 
 * <p>NOTE: Both model and view are not specified, however they should be implemented in a way that 
 * {@link startGame(Params)}
 * they have a refercence to the {@code InputController} and {@code KeyInputer},
 *  otherwise updates are like kicking air</p>
 * @param <C> the type of view component used for rendering {@code MutableObject}
 * @author Keegan Carlo Falcao
 */
public interface GameController<C> {

    /**
     * Loads the model. 
     * 
     * <p>NOTE: Model is not strictly specified, cause it can any, however it works only for platform type games
     * as {@link #startGame(InputController, KeyInputer)} states </p>
     * {@link #loadModel()}
     */
    void loadModel();

    /**
    * Sycs and inits the view.
    *
    * <p>NOTE: Should be called only after model is loaded. As model it is not strict coupled with a default view</p>
    *{@link #syncView()}
    */
    void syncView();

    /**
     * Start the game.
     * 
     * <p>NOTE: it's relative to game, in our case it is a platform type game, that's the reason why it needs 
     * {@code ControllableObject} and {łcode KeyInputer<K>} </p>
     * @param <K> type of key utiity used in view context → [JavaFX ({@code KeyCode}, Swing ({@code KeyEvent}])
     * @param inputController of a specified controllable entity
     * @param inputer it should call {@code KeyAssetter<K>} to pool the avaible pressed key
     */
    <K> void startGame(InputController<ControllableObject> inputController, KeyInputer<K> inputer, String type);

}
