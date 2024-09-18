package com.project.paradoxplatformer.model.effect.impl;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.abstracts.AbstractOneTimeEffect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.endGame.DeathObstacleCollisionCondition;

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
//            System.out.println(gameObject);
            DeathObstacleCollisionCondition.setDeath(true);
        });
    }

}
