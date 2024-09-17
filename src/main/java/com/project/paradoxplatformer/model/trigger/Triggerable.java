package com.project.paradoxplatformer.model.trigger;

/**
 * A functional interface that represents an action that can be triggered by a
 * {@link Trigger}.
 * <p>
 * Implementations of this interface define the behavior that will be executed
 * when the corresponding {@link Trigger} is activated.
 * </p>
 * <p>
 * This interface follows the functional interface pattern, meaning it contains
 * exactly one abstract method, {@code execute()}, which makes it eligible for
 * use with lambda expressions or method references.
 * </p>
 */
@FunctionalInterface
public interface Triggerable {

    /**
     * Executes the action that is triggered.
     * <p>
     * This method is called when a {@link Trigger} activates an action. The
     * specific behavior is defined in the implementation of this method.
     * </p>
     */
    void execute();
}
