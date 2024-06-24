package com.project.paradoxplatformer.view.fxcomponents.keyinputs;

import com.project.paradoxplatformer.view.fxcomponents.keyinputs.api.InputTranslator;
import com.project.paradoxplatformer.view.fxcomponents.keyinputs.api.InputType;

import javafx.scene.input.KeyCode;

public class KeyTranslatorFX implements InputTranslator {

    private final KeyCode keyCode;

    public KeyTranslatorFX(final KeyCode code) {
        this.keyCode = code;
    }

    @Override
    public InputType translate() {
        return InputType.getString(this.keyCode.getName());
    }

}
