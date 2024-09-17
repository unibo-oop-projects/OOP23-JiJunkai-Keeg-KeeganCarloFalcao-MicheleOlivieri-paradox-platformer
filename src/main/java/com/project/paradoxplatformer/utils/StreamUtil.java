package com.project.paradoxplatformer.utils;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Utiliy class for stream-based operations.
 * Such operation are not essential but improve code readibility.
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
     * Applys the map operation and then the resulting predicate within the original predicate.
     * So it basically maps the current stream element without performing a mapping operation throughout the stream.
     * 
     * <p> It enhance readibilty and is often used in the context of method refernce.</p>
     * @param <M> the initial elememnt type of the stream
     * @param <F> the mapped elememnt type of the stream
     * @param mapping the mapping function
     * @param pred the predicate to be applied to the mapping function
     * @return the original predicate with test result based on drugged predicate
     */
    public static <M, F> Predicate<M> mapAndFilter(final Function<M, F> mapping, final Predicate<F> pred) {
        return m -> pred.test(mapping.apply(m));
    }
}
