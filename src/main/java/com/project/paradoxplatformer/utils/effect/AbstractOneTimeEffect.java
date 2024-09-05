package com.project.paradoxplatformer.utils.effect;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.effect.api.Effect;
import com.project.paradoxplatformer.utils.effect.api.OneTimeEffect;

/**
 * Abstract implementation of a one-time effect that is applied and then
 * removed.
 */
public abstract class AbstractOneTimeEffect extends AbstractEffect implements OneTimeEffect {

    @Override
    public CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target,
            Optional<? extends CollidableGameObject> self) {
        return super.apply(target, self).thenRun(() -> {
            System.out.println("One-time effect has been applied and removed.");
        });
    }

    @Override
    public boolean isOneTimeEffect() {
        return true;
    }

    @Override
    public Effect recreate() {
        return null;
    }

}
