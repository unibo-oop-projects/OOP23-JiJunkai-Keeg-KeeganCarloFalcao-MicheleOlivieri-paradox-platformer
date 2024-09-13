package com.project.paradoxplatformer.utils;

import java.util.Objects;

@FunctionalInterface
public interface BiConsumerWithAndThen<T, U> {

    // Method to accept two parameters (similar to BiConsumer)
    void accept(T t, U u);

    // Default method to chain another BiConsumerWithAndThen
    default BiConsumerWithAndThen<T, U> andThen(BiConsumerWithAndThen<? super T, ? super U> after) {
        // Return a new BiConsumerWithAndThen that first executes the current one, and
        // then the 'after' one
        Objects.requireNonNull(after); // Ensure 'after' is not null
        return (T t, U u) -> {
            this.accept(t, u); // Execute the current action
            after.accept(t, u); // Execute the 'after' action
        };
    }
}
