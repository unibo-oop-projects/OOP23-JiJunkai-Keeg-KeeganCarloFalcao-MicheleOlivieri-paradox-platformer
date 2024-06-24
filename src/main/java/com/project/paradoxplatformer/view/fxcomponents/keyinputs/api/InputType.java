package com.project.paradoxplatformer.view.fxcomponents.keyinputs.api;

import java.util.Arrays;

public enum InputType {
    LEFT, RIGHT, UP,
    A, D, W,
    UNDEFINED;


    public static InputType getString(String name) {
        if(Arrays.stream(InputType.values()).map(InputType::name).anyMatch(name::equals)) {
            return valueOf(name);
        } 
        return UNDEFINED;
    }
}
