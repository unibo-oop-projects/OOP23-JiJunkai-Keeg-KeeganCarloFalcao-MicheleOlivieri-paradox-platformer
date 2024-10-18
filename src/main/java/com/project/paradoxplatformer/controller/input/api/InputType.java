package com.project.paradoxplatformer.controller.input.api;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum representing common input types for controller and model classes.
 * <p>
 * The {@code InputType} enum defines a set of standard input types used across
 * the application. These input types are used to abstract and unify the handling
 * of various keys and actions, allowing different components of the system to
 * operate with a consistent key representation.
 * </p>
 */
public enum InputType {
    /**
     * Represents the left movement key.
     */
    LEFT,

    /**
     * Represents the right movement key.
     */
    RIGHT,

    /**
     * Represents the upward movement key.
     */
    UP,

    /**
     * Represents the action key "A".
     */
    A,

    /**
     * Represents the action key "D".
     */
    D,

    /**
     * Represents the action key "W".
     */
    W,

    /**
     * Represents the escape key.
     */
    ESCAPE,

    /**
     * Represents an undefined key. This can be used as a default value
     * for keys that are not explicitly defined in the enum.
     */
    UNDEFINED,

    /**
     * Represents the action key "P".
     */
    P,

    /**
     * Represents the action key "R".
     */
    R,

    /**
     * Represents the action key "T".
     */
    T,

    /**
     * Represents the action key "K".
     */
    K;

    /**
     * Utility method to parse a string into an {@code InputType}.
     * <p>
     * This method attempts to find a matching {@code InputType} enum constant
     * based on the provided raw string. The string should exactly match one of
     * the enum constants' names.
     * </p>
     * <p>
     * This method is useful for converting string representations of keys into
     * the corresponding enum values, which are then used in the application
     * for consistent key handling.
     * </p>
     * 
     * @param inputStr the raw string representation of the key, typically coming
     *                 from the {@code toString()} methods of key-view components.
     * @return an {@code Optional<InputType>} containing the matching {@code InputType}
     *         if found. If no matching enum constant is found, it returns {@code Optional.empty()}.
     */
    public static Optional<InputType> getString(final String inputStr) {
        return Arrays.stream(values())
                .map(InputType::name)
                .filter(inputStr::equals)
                .findFirst()
                .map(InputType::valueOf);
    }
}
