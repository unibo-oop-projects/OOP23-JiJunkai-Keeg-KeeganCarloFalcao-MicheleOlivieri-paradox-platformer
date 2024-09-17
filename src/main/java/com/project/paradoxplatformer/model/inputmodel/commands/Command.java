package com.project.paradoxplatformer.model.inputmodel.commands;

/**
 * A functional interface representing a command that can be executed on an
 * actor.
 * <p>
 * This interface is intended to define a single action that can be performed on
 * a specified
 * actor of type {@code T}. It provides a method to execute the command with the
 * given actor.
 * </p>
 * 
 * @param <T> the type of the actor on which the command operates
 */
@FunctionalInterface
public interface Command<T> {

    /**
     * Executes the command on the given actor.
     * 
     * @param actor the actor on which the command is to be executed
     */
    void execute(T actor);
}
