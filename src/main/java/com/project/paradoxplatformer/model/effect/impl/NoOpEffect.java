package com.project.paradoxplatformer.model.effect.impl;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.AbstractOneTimeEffect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

/**
 * Represents an effect that performs no operation. Useful as a placeholder or
 * default effect.
 */
public class NoOpEffect extends AbstractOneTimeEffect {

    @Override
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        System.out.println("Nothing Happened.");
        return CompletableFuture.completedFuture(null);
    }

}
