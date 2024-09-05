package com.project.paradoxplatformer.utils.effect;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.effect.api.RecreateableEffect;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractRecreatableEffect extends AbstractEffect implements RecreateableEffect {

    @Override
    public CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target,
            Optional<? extends CollidableGameObject> self) {
        return super.apply(target, self).thenRun(() -> {
            System.out.println("Re-creatable effect has been applied and could be recreated.");
        });
    }

    @Override
    public boolean isOneTimeEffect() {
        return false;
    }

    @Override
    public abstract RecreateableEffect recreate();

}
