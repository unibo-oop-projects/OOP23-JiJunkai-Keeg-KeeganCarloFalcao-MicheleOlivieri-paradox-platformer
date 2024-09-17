package com.project.paradoxplatformer.controller.input.api;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum to create a common key bindings protocol for controller and model
 * classes.
 */
public enum InputType {
    /**
     * Left key string representative.
     */
    LEFT,
    /**
     * Right key string representative.
     */
    RIGHT,
    /**
     * Up key string representative.
     */
    UP,
    /**
     * A key string representative.
     */
    A,
    /**
     * D key string representative.
     */
    D,
    /**
     * W key string representative.
     */
    W,
    /**
     * Esc key string representative.
     */
    ESCAPE,
    /**
     * Undefined key.
     */
    UNDEFINED,
    /**
     * P key string representative.
     */
    P,
    /**
     * R key string representative.
     */
    R,
    /**
     * T key string representative.
     */
    T,
    /**
     * K key string representative.
     */
    K;

    /**
     * Utility static method to parse a string to an input type. It finds the raw
     * string
     * matching in the enum values, without any modifications to the string itself.
     * 
     * @param inputStr raw (unmodified) input key string coming from the toString()
     *                 methods of key-view.
     * @return the matching InputType found in the enum values.
     */
    public static Optional<InputType> getString(final String inputStr) {
        return Arrays.stream(InputType.values())
                .map(InputType::name)
                .filter(inputStr::equals)
                .findFirst()
                .map(InputType::valueOf);
    }
}
