package com.project.paradoxplatformer.controller.input.api;

import java.util.Optional;
import java.util.Set;

/**
 * Interface for setting input keys.
 * @param <K> type of view key
 * @author Keegan Carlo Falcao
 */
public interface KeyAssetter<K> {
    /**
     * Removes a key when it is not pressed from the pool of current keys.
     * @param e the view key to be removed
     * @return {@code false} if was not able to remove it
     */
    boolean remove(K e);

    /**
     * * Adds a key when it is not pressed from the pool of current keys.
     * @param e the view key to be added 
     * @return {@code false} if was not able to add it, i.e duplicate keys
     */
    boolean add(K e);

    /**
     * Useful for doing any computions for current pool of keys.
     * @return {@code Set<Optional<InputType>>} unmodifiable because caller cannot modify, 
     * it is mangaged upon this interface.
     * So to make class immutable, implementation needs to create a defensive copy
     */
    Set<Optional<InputType>> getUnmodifiablePool();
}
