package com.project.paradoxplatformer.utils.collision.api;

import java.util.Optional;

import java.util.concurrent.CompletableFuture;

public interface Effect {
    CompletableFuture<Void> apply(Optional<Object> target);
}