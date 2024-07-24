package com.project.paradoxplatformer.controller.input.api;

import java.util.Set;

/**
 * Interface for setting input keys
 */
public interface KeyAssetter<K> {
    /**
     * Removes a key when it is not pressed from the pool of current keys
     * @param e
     * @return
     */
    public boolean remove(final K e);

    /**
     * * Adds a key when it is not pressed from the pool of current keys
     * @param e
     * @return
     */
    public boolean add(final K e);

    /**
     * Useful for doing any computions for current pool of keys
     * @return unmodifiable because caller cannot modify, it is mangaged upon this interface.
     * So to make class immutable, implementation needs to create a defensive copy
     */
    public Set<InputType> getUnmodifiablePool();
}
