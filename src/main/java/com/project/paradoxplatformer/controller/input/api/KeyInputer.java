package com.project.paradoxplatformer.controller.input.api;

/**
 * Interface that bridges the gap between a graphical container and an input controller.
 * <p>
 * The {@code KeyInputer} interface is intended to be used with components that can capture
 * key events, such as panels or other container components. It serves as an intermediary,
 * allowing key inputs to be processed and managed by the {@code InputController}.
 * </p>
 * <p>
 * It is recommended to use this interface with components that can listen for key inputs
 * rather than individual components. By capturing key events at the container level, 
 * you ensure a safer and more reliable method for handling input events across the
 * entire component.
 * </p>
 * 
 * @param <K> the type of key used by the view. This type represents the keys that are
 *            processed and managed by the {@code KeyInputer}.
 */
public interface KeyInputer<K> {

    /**
     * Retrieves an immutable {@code KeyAssetter<K>} which provides the current state
     * of key assets.
     * <p>
     * This method should be called during the game loop to obtain the current key
     * assets, which are necessary for processing input commands. The {@code KeyAssetter}
     * provides a read-only view of the key assets, ensuring that the input state is
     * not modified directly.
     * </p>
     * 
     * @return the {@code KeyAssetter<K>} instance that contains the current key assets.
     *         This instance is used by the {@code InputController} to map and execute
     *         commands based on the current key inputs.
     */
    KeyAssetter<K> getKeyAssetter();

    /**
     * Sets the key event handlers or listeners for capturing key inputs.
     * <p>
     * This method is particularly useful for UI classes or panels that need to activate
     * keyboard input handling. By providing a {@code Runnable} that activates the key
     * input, you can ensure that the current pane or component is capable of receiving
     * and processing keyboard events.
     * </p>
     * 
     * @param activateInput a {@code Runnable} that, when executed, will activate the
     *                      keyboard input for the current pane or component. This
     *                      activation allows the component to start listening for key
     *                      events and handle them appropriately.
     */
    void activateKeyInput(Runnable activateInput);
}
