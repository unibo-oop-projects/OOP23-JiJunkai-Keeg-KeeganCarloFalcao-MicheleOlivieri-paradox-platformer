package com.project.paradoxplatformer.controller.input.api;

import java.util.Arrays;
import java.util.Optional;

/**
 * Made to create a common key bindings protocol for controller and model's classes
 */
public enum InputType {
    /**
     * Left key String representative
     */
    LEFT,
    /**
     * Right key String representative
     */
    RIGHT, 
    /**
     * Up key String representative
     */
    UP,
    /**
     * A key String representative
     */
    A,
    /**
     * D key String representative
     */ 
    D,
    /**
     * W key String representative
     */ 
    W,
    /**
     * Esc key String representative
     */
    ESCAPE,
    /**
     * Undefined key
     */
    UNDEFINED,
    /**
     * P key String representative
     */ 
    P,
    /**
     * R key String representative
     */ 
    R,
    /**
     * T key String representative
     */ 
    T,
    /**
     * K key String representative
     */ 
    K;

    /**
     * Utility static method to parse a string to an input type. So it basically finds the raw string 
     * matching in the enum values, withoud any modifications to the string itself.
     * @param inputStr raw (unmodified) input key string coming from the toString() methods of key-view.  
     * @return the matching string found in the values array.
     */
    public static Optional<InputType> getString(final String inputStr) {
        return Arrays.stream(InputType.values())
            .map(InputType::name)
            .filter(inputStr::equals)
            .findFirst()
            .map(InputType::valueOf);
        
    }
}
