package com.project.paradoxplatformer.model.effect.impl;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.abstracts.AbstractRecreatableEffect;
import com.project.paradoxplatformer.model.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

/**
 * Represents an effect that jumps a player automatically. 
 */
public final class SpringEffect extends AbstractRecreatableEffect {

    /**
     * Non argument constructor.
     */
    public SpringEffect() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecreateableEffect recreate() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected CompletableFuture<Void> applyToGameObject(final CollidableGameObject gameObject) {
        return CompletableFuture.runAsync(() -> {
            if (gameObject instanceof ControllableObject) {
                ((ControllableObject) gameObject).jump();
            }
        });
    }
}
