package com.project.paradoxplatformer.utils.effect.api;

import java.util.Optional;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;

public interface Effect {
    CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target,
            Optional<? extends CollidableGameObject> self);

    default boolean isOneTimeEffect() {
        return false;
    }

    default Effect recreate() {
        return this;
    }
}
