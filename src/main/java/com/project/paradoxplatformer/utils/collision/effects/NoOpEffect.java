package com.project.paradoxplatformer.utils.collision.effects;

import com.project.paradoxplatformer.utils.collision.api.Effect;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class NoOpEffect implements Effect {
    @Override
    public CompletableFuture<Void> apply(Optional<Object> target) {
        return CompletableFuture.completedFuture(null); // No operation
    }
}