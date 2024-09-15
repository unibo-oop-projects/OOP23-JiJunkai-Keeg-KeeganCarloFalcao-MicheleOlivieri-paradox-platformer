package com.project.paradoxplatformer.model.inputmodel;

import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

import java.util.Map;

/**
 * Represents a model that maps input types to commands for a specific type of
 * actor.
 * <p>
 * The {@code InputModel} interface provides a way to associate different
 * {@link InputType}
 * instances with corresponding {@link Command} objects that can be executed on
 * a given actor.
 * </p>
 *
 * @param <T> the type of the actor that the commands will operate on
 */
public interface InputModel<T> {

    /**
     * Retrieves the mapping of {@link InputType} to {@link Command}.
     * 
     * @return a {@link Map} where keys are {@link InputType} instances and values
     *         are {@link Command} instances
     */
    Map<InputType, Command<T>> getModel();
}
