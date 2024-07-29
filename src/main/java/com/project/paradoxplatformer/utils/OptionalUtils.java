package com.project.paradoxplatformer.utils;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.function.Function;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Helper class for easy, readable piping methods.
 * All methods should be declared as static
 */
public final class OptionalUtils {
    
    /**
     * It Basically wraps a map function into a consumer function, behaves like 
     * {@link Stream#peek(Consumer)}
     * Performs the action and returns the original, must be aggregegated to a 
     * {@link Optional#map(Function)} function.
     * @param <T> The type of the value returned from the mapping function
     * @param action consuming action
     * @return an unary operator function which resembles a mapping function required by {@link Optional#map(Function)}
     * @see {@link Optional}, {@link Stream}
     */
    public static <T> UnaryOperator<T> peek(Consumer<T> action) {
        return t -> {
            action.accept(t);
            return t;
        };
    }
}
