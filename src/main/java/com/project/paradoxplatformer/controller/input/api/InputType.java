package com.project.paradoxplatformer.controller.input.api;

import java.util.Arrays;

public enum InputType {
    LEFT, RIGHT, UP,
    A, D, W,
    ESCAPE,
    UNDEFINED, P, R;


    public static InputType getString(final String name) {
        if(Arrays.stream(InputType.values()).map(InputType::name).anyMatch(name::equals)) {
            return valueOf(name);
        } 
        return UNDEFINED;
    }
}
