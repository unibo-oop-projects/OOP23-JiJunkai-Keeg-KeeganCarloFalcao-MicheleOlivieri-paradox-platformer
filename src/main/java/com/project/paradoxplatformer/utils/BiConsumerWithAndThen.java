package com.project.paradoxplatformer.utils;

import java.util.Objects;

/**
 * A functional interface similar to {@code BiConsumer}, but with the ability to
 * chain multiple actions using the {@code andThen} method.
 *
 * @param <T> the type of the first argument to the operation
 * @param <U> the type of the second argument to the operation
 */
@FunctionalInterface
public interface BiConsumerWithAndThen<T, U> {

    /**
     * Performs the operation on the given arguments.
     *
     * @param t the first input argument
     * @param u the second input argument
     */
    void accept(T t, U u);

    /**
     * Chains another {@code BiConsumerWithAndThen} to be executed after the current
     * one.
     * The second consumer will only be executed if the current consumer runs
     * successfully.
     *
     * @param after the consumer to be executed after the current consumer
     * @return a new {@code BiConsumerWithAndThen} that performs the current
     *         operation followed by the {@code after} operation
     * @throws NullPointerException if the {@code after} consumer is null
     */
    default BiConsumerWithAndThen<T, U> andThen(BiConsumerWithAndThen<? super T, ? super U> after) {
        // Ensure the 'after' consumer is not null to prevent null pointer exceptions
        Objects.requireNonNull(after);

        // Return a new BiConsumerWithAndThen that first executes the current consumer
        // and then the 'after' consumer
        return (T t, U u) -> {
            this.accept(t, u); // Execute the current action
            after.accept(t, u); // Execute the 'after' action
        };
    }
}
