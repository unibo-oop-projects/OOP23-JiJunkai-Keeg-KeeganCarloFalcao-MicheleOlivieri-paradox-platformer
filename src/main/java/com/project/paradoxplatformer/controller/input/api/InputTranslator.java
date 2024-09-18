package com.project.paradoxplatformer.controller.input.api;

import java.util.Optional;

/**
 * An interface designed to translate view-specific key types into a common
 * {@code InputType} enumeration.
 * <p>
 * The {@code InputTranslator} interface allows for the mapping of view-specific keys,
 * such as keyboard or controller inputs, to a standardized {@code InputType} enum. 
 * This translation enables the controller or other system components to work with a
 * unified key representation, making it independent of specific view key types.
 * </p>
 * <p>
 * <b>Note:</b> The {@code translate} method must adhere to the constants defined in
 * the {@code InputType} enum. This implies that the {@code InputType} enum should be
 * updated with new constants as needed to ensure compatibility with various view key types.
 * </p>
 * 
 * @param <K> the type of view key being translated. This represents the keys as they
 *            are handled by the view layer, such as keyboard keys or game controller
 *            buttons.
 */
@FunctionalInterface
public interface InputTranslator<K> {

    /**
     * Translates a view-specific key into a common {@code InputType} enum.
     * <p>
     * This method converts a view-specific key type (e.g., a keyboard key or game
     * controller button) into a standardized {@code InputType} value. The translation
     * allows the controller or model classes to operate with a consistent key representation
     * without being directly tied to specific view-related key classes.
     * </p>
     * <p>
     * If translation is not possible—typically because the {@code InputType} enum does
     * not include a corresponding constant for the provided key—the method returns an
     * {@code Optional.empty()}.
     * </p>
     * 
     * @param keyType the view-specific key to be translated. This represents the key as
     *                it is recognized by the view layer, and it will be mapped to a
     *                corresponding {@code InputType}.
     * @return an {@code Optional<InputType>} containing the translated {@code InputType}
     *         if the translation is successful. If the translation fails (e.g., due to
     *         the absence of a corresponding constant in {@code InputType}), the method
     *         returns {@code Optional.empty()}.
     */
    Optional<InputType> translate(K keyType);
}
