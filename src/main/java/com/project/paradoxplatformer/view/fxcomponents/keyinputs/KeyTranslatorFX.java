package com.project.paradoxplatformer.view.fxcomponents.keyinputs;

import javafx.scene.input.KeyCode;

public class KeyTranslatorFX implements InputTranslator {

    private KeyCode keyCode;

    public KeyTranslatorFX(KeyCode code) {
        this.keyCode = code;
    }

    @Override
    public InputType translate() {
        return InputType.valueOf(this.keyCode.getName());
    }

}
