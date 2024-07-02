package com.project.paradoxplatformer.controller.input.api;

public interface KeyInputer {

    KeyAssetter getKeyAssetter();

    //Useful for fx as it needs to request the focus
    void activateKeyInput(final Runnable activateInput);
    
    void setKeyPressed();

    void setKeyReleased();
}
