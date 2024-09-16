package com.project.paradoxplatformer.utils;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamUtil {

    /**
     * Converts an Iterator to a Stream.
     * 
     * @param <T>      the type of elements in the Iterator
     * @param iterator the Iterator to convert
     * @return a Stream containing the elements of the Iterator
     */
    public static <T> Stream<T> toStream(Iterator<T> iterator) {
        return StreamSupport.stream(
                ((Iterable<T>) () -> iterator).spliterator(),
                false);
    }
}