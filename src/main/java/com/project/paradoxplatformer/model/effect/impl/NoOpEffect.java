package com.project.paradoxplatformer.model.effect.impl;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.abstracts.AbstractOneTimeEffect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

/**
 * Represents an effect that performs no operation. Useful as a placeholder or
 * default effect.
 */
public final class NoOpEffect extends AbstractOneTimeEffect {

    /**
     * Applies this effect to the given game object. This effect performs no
     * operation and simply logs a message.
     * 
     * @param gameObject the {@link CollidableGameObject} to which this effect is
     *                   applied. This parameter is final and should not be
     *                   modified.
     * @return a {@link CompletableFuture} that is completed when the effect has
     *         been
     *         applied.
     */
    @Override
    protected CompletableFuture<Void> applyToGameObject(final CollidableGameObject gameObject) {
        System.out.println("Nothing Happened.");
        return CompletableFuture.completedFuture(null);
    }
}
