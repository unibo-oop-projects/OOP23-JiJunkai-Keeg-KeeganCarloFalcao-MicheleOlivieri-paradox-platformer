package com.project.paradoxplatformer.utils;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Utility class for common operations on lists and iterators.
 * <p>
 * This class contains static methods to perform operations such as converting
 * an
 * {@link Iterator} to a {@link List}. It cannot be instantiated.
 * </p>
 */
public final class ListUtil {

    // Private constructor to prevent instantiation
    private ListUtil() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * Converts an Iterator to a List.
     * 
     * @param <T>      the type of elements in the Iterator
     * @param iterator the Iterator to convert
     * @return a List containing the elements of the Iterator
     */
    public static <T> List<T> toList(final Iterator<T> iterator) {
        return StreamSupport.stream(
                ((Iterable<T>) () -> iterator).spliterator(),
                false).collect(Collectors.toList());
    }
}
