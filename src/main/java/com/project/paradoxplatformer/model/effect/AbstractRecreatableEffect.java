package com.project.paradoxplatformer.model.effect;

import com.project.paradoxplatformer.model.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Abstract implementation of a recreatable effect that can be applied and
 * recreated.
 */
public abstract class AbstractRecreatableEffect extends AbstractEffect implements RecreateableEffect {

    @Override
    public CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target,
            Optional<? extends CollidableGameObject> self) {
        return super.apply(target, self).thenRun(() -> {
            // System.out.println("Re-creatable effect has been applied and could be recreated.");
        });
    }

    @Override
    public boolean isOneTimeEffect() {
        return false;
    }

    @Override
    public abstract RecreateableEffect recreate();

}
