package com.project.paradoxplatformer.utils;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Utiliy class for stream-based operations.
 * Such operations are not essential but improves code readibility.
 */
public final class StreamUtil {

    /**
     * private constructor to prevent initialization.
     */
    private StreamUtil() {

    }
    /**
     * Converts an Iterator to a Stream.
     * 
     * @param <T>      the type of elements in the Iterator
     * @param iterator the Iterator to convert
     * @return a Stream containing the elements of the Iterator
     */
    public static <T> Stream<T> toStream(final Iterator<T> iterator) {
        return StreamSupport.stream(
                ((Iterable<T>) () -> iterator).spliterator(),
                false);
    }

    /**
     * Applies a mapping function to elements of the stream and then filters the results 
     * using a given predicate. This method combines the mapping and filtering operations into 
     * a single predicate that can be used for stream filtering.
     * 
     * <p>This method allows you to perform a mapping operation on each element of the stream, 
     * followed by a filtering operation based on the result of the mapping. It enhances readability 
     * and is often used in the context of method references to create more concise and expressive 
     * stream processing code.</p>
     * 
     * <p>For example, if you have a stream of strings and want to filter those strings based on 
     * their length, you can use this method to first map each string to its length and then filter 
     * based on whether the length meets a certain condition.</p>
     * 
     * 
     * @param <M> the type of the elements in the original stream
     * @param <F> the type of the elements after the mapping operation
     * @param mapping the function to map elements from type {@code M} to type {@code F}
     * @param pred the predicate to test elements of type {@code F}
     * @return a predicate that first maps each element of type {@code M} to type {@code F} using 
     *         the {@code mapping} function, and then applies the {@code pred} predicate to the result.
     * 
     * @throws NullPointerException if {@code mapping} or {@code pred} is {@code null}
     */
    public static <M, F> Predicate<M> mapAndFilter(final Function<M, F> mapping, final Predicate<F> pred) {
        return m -> pred.test(mapping.apply(m));
    }

}
