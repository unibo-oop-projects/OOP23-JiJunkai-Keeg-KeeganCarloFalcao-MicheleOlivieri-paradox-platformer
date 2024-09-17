package com.project.paradoxplatformer.model.effect.impl;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.abstracts.AbstractOneTimeEffect;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractDeathObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

/**
 * An effect that causes the end of the game.
 * This effect is a one-time effect, meaning it can only be applied once.
 */
public final class DeathEffect extends AbstractOneTimeEffect {

    /**
     * {@inheritDoc}
     */
    @Override
    protected CompletableFuture<Void> applyToGameObject(final CollidableGameObject gameObject) {
        return CompletableFuture.runAsync(() -> {
            ((AbstractDeathObstacle) ((ControllableObject) gameObject)).onCollision(Optional.empty());
        });
    }

    @Override
    protected CompletableFuture<Void> applyToTarget(final Optional<? extends CollidableGameObject> target) {
        return CompletableFuture.runAsync(() -> {
            target.ifPresent(t -> {
                if (t instanceof ControllableObject) {
                    ((ControllableObject) t).onCollision();
                    // System.out.println("DeathEffect applied: Player killed.");
                }
            });
        });
    }

}
