package com.project.paradoxplatformer.utils.effect;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;

/**
 * Represents an effect that performs no operation. Useful as a placeholder or
 * default effect.
 */
public class NoOpEffect extends AbstractEffect {

    @Override
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        return CompletableFuture.completedFuture(null); // No operation
    }
}
