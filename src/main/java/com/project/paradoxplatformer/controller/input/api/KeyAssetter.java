package com.project.paradoxplatformer.controller.input.api;

import java.util.Optional;
import java.util.Set;

/**
 * Manages and maintains the state of keys pressed during gameplay.
 * <p>
 * The {@code KeyAssetter} interface is responsible for tracking which keys are currently
 * pressed and managing these keys within a pool. It provides methods to add and remove
 * keys from this pool, as well as to retrieve an unmodifiable view of the current key pool.
 * </p>
 * 
 * @param <K> the type of view key used by the {@code KeyAssetter}. This type represents
 *            the keys that are tracked by the key assetter.
 * @author Keegan Carlo Falcao
 */
public interface KeyAssetter<K> {

    /**
     * Removes a key from the pool of currently pressed keys.
     * <p>
     * This method is used to update the key pool when a key is released or no longer
     * relevant. If the key was present in the pool, it will be removed; otherwise, the
     * pool remains unchanged.
     * </p>
     * 
     * @param e the view key to be removed from the pool. This represents the key that
     *          is no longer pressed or should be excluded from tracking.
     * @return {@code true} if the key was successfully removed from the pool; {@code false}
     *         if the key was not found in the pool or if it could not be removed for any
     *         reason.
     */
    boolean remove(K e);

    /**
     * Adds a key to the pool of currently pressed keys.
     * <p>
     * This method updates the key pool when a key is pressed or needs to be tracked.
     * If the key is already present in the pool (i.e., it is a duplicate), the pool will
     * remain unchanged.
     * </p>
     * 
     * @param e the view key to be added to the pool. This represents the key that is
     *          currently pressed or needs to be included in tracking.
     * @return {@code true} if the key was successfully added to the pool; {@code false}
     *         if the key was already present (i.e., a duplicate) or if it could not be
     *         added for any reason.
     */
    boolean add(K e);

    /**
     * Provides an unmodifiable view of the current pool of keys.
     * <p>
     * This method returns a set containing the current keys tracked by the key assetter.
     * Each key is represented as an {@code Optional<InputType>}, which allows for
     * the possibility of missing or null values in the pool. The returned set is
     * immutable to ensure that the caller cannot modify the key pool directly.
     * </p>
     * <p>
     * Implementations are encouraged to return a defensive copy of the key pool to
     * prevent external modifications and ensure that the internal state of the key
     * assetter remains consistent.
     * </p>
     * 
     * @return an unmodifiable {@code Set<Optional<InputType>>} representing the current
     *         pool of keys. The set is immutable, and it is recommended that a defensive
     *         copy is returned to prevent external modifications.
     */
    Set<Optional<InputType>> getUnmodifiablePool();
}
