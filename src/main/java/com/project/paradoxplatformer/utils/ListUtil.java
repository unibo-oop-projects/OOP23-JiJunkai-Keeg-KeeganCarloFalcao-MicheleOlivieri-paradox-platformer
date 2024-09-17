package com.project.paradoxplatformer.utils;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ListUtil {

    /**
     * Converts an Iterator to a List.
     * 
     * @param <T>      the type of elements in the Iterator
     * @param iterator the Iterator to convert
     * @return a List containing the elements of the Iterator
     */
    public static <T> List<T> toList(Iterator<T> iterator) {
        return StreamSupport.stream(
                ((Iterable<T>) () -> iterator).spliterator(),
                false).collect(Collectors.toList());
    }
}