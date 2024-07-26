package com.project.paradoxplatformer.controller.input.api;

import java.util.Arrays;
import java.util.Optional;

public enum InputType {
    LEFT, RIGHT, UP,
    A, D, W,
    ESCAPE,
    UNDEFINED, P, R, T, K;

    /**
     * Utility static method to parse a string to an input type.
     * @param inputStr
     * @return
     */
    public static Optional<InputType> getString(final String inputStr) {
        return Arrays.stream(InputType.values())
            .map(InputType::name)
            .filter(inputStr::equals)
            .findFirst()
            .map(InputType::valueOf);
        
    }
}
