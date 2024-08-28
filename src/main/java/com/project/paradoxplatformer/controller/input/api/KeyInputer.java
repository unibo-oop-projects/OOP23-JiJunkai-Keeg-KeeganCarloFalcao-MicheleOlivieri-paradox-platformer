package com.project.paradoxplatformer.controller.input.api;

/**
 * Key Inputer serves as a bridge from GraphicContainer to the InputController.
 * <p> NOTE: It is strongly suggested to bridge suche interface to Panels, aka components containers, 
 * which are able to listen key input
 * In such case, it has the responsabilty to capture any key events, which is safer rather than a component
 * 
 * @param <K> type of view key
 */
public interface KeyInputer<K> {

    /**
     * Getter of an immutable {@code KeyAssetter<K>}, which should be called on the gameloop
     * @return {@link #getKeyAssetter()}
     */
    KeyAssetter<K> getKeyAssetter();

    /**
     * Sets the key event handlers/listeners
     * @param activateInput Very Useful for dedicated UI classes to activate the keyboard input to current pane.
     */
    void activateKeyInput(Runnable activateInput);

}
