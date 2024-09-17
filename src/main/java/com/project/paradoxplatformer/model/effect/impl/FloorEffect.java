package com.project.paradoxplatformer.model.effect.impl;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.abstracts.AbstractRecreatableEffect;
import com.project.paradoxplatformer.model.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

/**
 * Effect applied to prevent from falling upon a ground level (or a platfrom).
 */
public final class FloorEffect extends AbstractRecreatableEffect {

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
            if (gameObject instanceof ControllableObject controllableObject) {
                controllableObject.stopFall();
            }
        });
    }

}
