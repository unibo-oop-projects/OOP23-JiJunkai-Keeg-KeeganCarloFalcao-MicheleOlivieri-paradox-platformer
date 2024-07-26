package com.project.paradoxplatformer.controller.input.api;

import java.util.Optional;
/**
 * Very useful interface to translate the view key handling to a common {@code InputType} enum.
 * So the controller works unaware of view key type
 * 
 * <p>NOTE: translate must adhere to InputType enum costants and not viceversa, so make an utily method in {@code InputType}</p>
 */
@FunctionalInterface
public interface InputTranslator<K> {
    /**
     * 
     * @param keyType type of view key
     * @return {@code Optional<InputType>} in case translate was not possible. Most probably {@code InputType} has not updated its costants
     */
    Optional<InputType> translate(K keyType);
}
