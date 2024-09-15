package com.project.paradoxplatformer.controller.input;

import java.util.Set;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import com.project.paradoxplatformer.controller.input.api.InputTranslator;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.controller.input.api.KeyAssetter;

/**
 * Implementation of the {@link KeyAssetter} interface for managing a set of key
 * assets.
 * <p>
 * This class uses an {@code InputTranslator} to convert key inputs to
 * {@code InputType} and provides methods to add and remove key assets from the
 * pool.
 * </p>
 * 
 * @param <K> the type of key used in the key assetter
 */
public final class KeyAssetterImpl<K> implements KeyAssetter<K> {

    private final Set<Optional<InputType>> pool;
    private final InputTranslator<K> translator;

    /**
     * Constructs a {@code KeyAssetterImpl} with the specified
     * {@code InputTranslator}.
     * 
     * @param translator the input translator used to convert keys to input types
     */
    public KeyAssetterImpl(final InputTranslator<K> translator) {
        this.pool = new HashSet<>();
        this.translator = translator;
    }

    /**
     * Constructs a {@code KeyAssetterImpl} by copying from another
     * {@code KeyAssetter}.
     * 
     * @param copy the {@code KeyAssetter} to copy from
     */
    public KeyAssetterImpl(final KeyAssetter<K> copy) {
        this.pool = new HashSet<>(copy.getUnmodifiablePool());
        this.translator = null; // Translator must be set separately
    }

    /**
     * Returns an unmodifiable view of the key assets pool.
     * 
     * @return an unmodifiable set of key assets
     */
    @Override
    public Set<Optional<InputType>> getUnmodifiablePool() {
        return Collections.unmodifiableSet(pool);
    }

    /**
     * Removes the specified key from the pool.
     * 
     * @param e the key to be removed
     * @return {@code true} if the key was removed, {@code false} otherwise
     */
    @Override
    public boolean remove(final K e) {
        return this.pool.remove(translator.translate(e));
    }

    /**
     * Adds the specified key to the pool.
     * 
     * @param e the key to be added
     * @return {@code true} if the key was added, {@code false} otherwise
     */
    @Override
    public boolean add(final K e) {
        return this.pool.add(translator.translate(e));
    }
}
