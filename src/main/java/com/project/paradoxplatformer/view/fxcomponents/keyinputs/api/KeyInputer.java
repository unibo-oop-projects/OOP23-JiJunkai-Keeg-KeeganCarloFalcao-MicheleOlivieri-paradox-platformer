package com.project.paradoxplatformer.view.fxcomponents.keyinputs.api;

public interface KeyInputer {

    FXKeyAssetter getKeyAssetter();

    void activateKeyInput();
    
    void setKeyPressed();

    void setKeyReleased();
}
