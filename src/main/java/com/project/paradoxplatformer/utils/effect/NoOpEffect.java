package com.project.paradoxplatformer.utils.effect;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.effect.api.Effect;

public class NoOpEffect implements Effect {
    
    @Override
    public CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target) {
        return CompletableFuture.completedFuture(null); // No operation
    }
}