package com.project.paradoxplatformer.controller.input.api;

import java.util.Optional;
import java.util.Set;

/**
 * KeyAsseter handles what is pressed during the gameplay and holds the logs to a pool.
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
     * Useful for doing any computions to current pool of keys.
     * @return {@code Set<Optional<InputType>>} an unmodifiable set because caller cannot modify it.
     * So it is required to make this set immutable, (suggested: implementation needs to create a defensive copy).
     */
    Set<Optional<InputType>> getUnmodifiablePool();
}
