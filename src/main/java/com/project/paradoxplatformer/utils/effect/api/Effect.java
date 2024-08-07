package com.project.paradoxplatformer.utils.effect.api;

import java.util.Optional;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.utils.collision.api.Collidable;

public interface Effect {
    CompletableFuture<Void> apply(Optional<? extends Collidable> target);
}