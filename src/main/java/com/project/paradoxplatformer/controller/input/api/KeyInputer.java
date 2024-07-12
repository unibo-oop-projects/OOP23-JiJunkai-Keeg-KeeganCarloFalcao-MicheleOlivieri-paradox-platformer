package com.project.paradoxplatformer.controller.input.api;

import com.project.paradoxplatformer.utils.SecureWrapper;

public interface KeyInputer {

    SecureWrapper<KeyAssetter> getKeyAssetter();

    //Useful for fx as it needs to request the focus
    void activateKeyInput(final Runnable activateInput);
    
    void setKeyPressed();

    void setKeyReleased();

    void setKeyTyped();
}
