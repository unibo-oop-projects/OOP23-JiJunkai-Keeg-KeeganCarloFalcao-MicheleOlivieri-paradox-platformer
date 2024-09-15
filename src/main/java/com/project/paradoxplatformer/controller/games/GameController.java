package com.project.paradoxplatformer.controller.games;

import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.controller.input.api.KeyInputer;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;

/**
 * Interface for controlling a game, including loading the model, syncing with
 * the view,
 * and starting the game. The methods must be called sequentially to ensure
 * proper game setup.
 * <p>
 * Note: Both the model and view are not strictly specified in this interface.
 * They should
 * be implemented in a way that they can interact with {@link InputController}
 * and {@link KeyInputer}.
 * If they do not, the updates may be ineffective.
 * </p>
 * 
 * @param <C> the type of view component used for rendering
 *            {@code MutableObject}
 * @param <K> the type of key utility used in the view context (e.g., JavaFX
 *            ({@code KeyCode}), Swing ({@code KeyEvent}))
 * @author Keegan Carlo Falcao
 */
public interface GameController<C> {

    /**
     * Loads the game model.
     * <p>
     * Note: The model is not strictly specified as it can vary, but it should work
     * for platform-type games as indicated in
     * {@link #startGame(InputController, KeyInputer, String)}.
     * </p>
     */
    void loadModel();

    /**
     * Syncs and initializes the view.
     * <p>
     * This method should only be called after the model has been loaded. The model
     * is not strictly coupled with a default view, so syncing is necessary.
     * </p>
     */
    void syncView();

    /**
     * Starts the game.
     * <p>
     * The behavior of this method is specific to the game type. For platform-type
     * games,
     * it requires a {@code ControllableObject} and {@code KeyInputer<K>} for
     * handling
     * user inputs.
     * </p>
     * 
     * @param inputController the controller managing the controllable entity
     * @param inputer         the key utility holding key logs; it should call
     *                        {@code KeyAssetter<K>} to pool the available pressed
     *                        keys
     * @param type            the type of the game to be started
     * 
     * @param <K>             the type of key utility used in the view context
     *                        (e.g., JavaFX ({@code KeyCode}), Swing
     *                        ({@code KeyEvent}))
     */
    <K> void startGame(InputController<ControllableObject> inputController, KeyInputer<K> inputer, String type);

    /**
     * Restarts the game.
     * <p>
     * This method resets the game state and reloads necessary components.
     * </p>
     */
    void restartGame();

    /**
     * Exits the game.
     * <p>
     * This method handles the cleanup and termination of the game session.
     * </p>
     */
    void exitGame();
}
