package com.project.paradoxplatformer.utils.effect.api;

import java.util.Optional;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;

public interface Effect {
    public CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target,
            Optional<? extends CollidableGameObject> self);

    default CompletableFuture<Void> empty() {
        return CompletableFuture.completedFuture(null);
    }
}