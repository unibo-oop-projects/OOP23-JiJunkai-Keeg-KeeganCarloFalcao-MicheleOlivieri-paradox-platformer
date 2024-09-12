package com.project.paradoxplatformer.controller.input.api;

import java.util.Optional;
/**
 * Very useful interface to translate the view key handling to a common {@code InputType} enum.
 * So the controller works unaware of view key type
 * 
 * <p>NOTE: translate must adhere to InputType enum costants and not viceversa, so make an utily method in 
 * {@code InputType}</p>
 * @param <K> type for view key
 */
@FunctionalInterface
public interface InputTranslator<K> {
    /**
     * translates an external keyboard related keys to a common protocol know as {@link InputType}, which allows controller or model's
     * classes to communicate to view without changing their implementation to use view-related Key classes 
     * @param keyType type of view key
     * @return {@code Optional<InputType>} in case translate was not possible. In case it happens it mostly due to {@code InputType} 
     * enum has not updated its costants yet.
     */
    Optional<InputType> translate(K keyType);
}
