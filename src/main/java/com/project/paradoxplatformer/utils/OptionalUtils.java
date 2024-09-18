package com.project.paradoxplatformer.utils;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * Helper class for easy, readable piping methods.
 * All methods should be declared as static
 */
public final class OptionalUtils {

    // Private constructor to prevent instantiation
    private OptionalUtils() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * It Basically wraps a map function into a consumer function, behaves like
     * {@link java.util.stream.Stream#peek(Consumer)}
     * Performs the action and returns the original, must be aggregegated to a
     * {@link java.util.Optional#map(Function)} function.
     * 
     * @param <T>    The type of the value returned from the mapping function
     * @param action consuming action
     * @return an unary operator function which resembles a mapping function
     *         required by {@link java.util.Optional#map(Function)}
     * @see java.util.Optional
     * @see java.util.stream.Stream
     */
    public static <T> UnaryOperator<T> peek(final Consumer<T> action) {
        return t -> {
            action.accept(t);
            return t;
        };
    }
}
