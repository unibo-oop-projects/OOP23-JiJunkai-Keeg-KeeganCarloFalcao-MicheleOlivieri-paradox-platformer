package com.project.paradoxplatformer.utils.collision;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.paradoxplatformer.utils.collision.api.Effect;

import java.util.concurrent.CompletableFuture;

public class ChainOfEffects {
    private final List<Effect> effects;

    private ChainOfEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public CompletableFuture<Void> applyEffectsSequentially(Optional<Object> target) {
        CompletableFuture<Void> future = CompletableFuture.completedFuture(null);
        for (Effect effect : effects) {
            future = future.thenCompose(v -> effect.apply(target));
        }
        return future;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final List<Effect> effects = new ArrayList<>();

        public Builder addEffect(Effect effect) {
            effects.add(effect);
            return this;
        }

        public Builder addEffects(List<Effect> effects) {
            this.effects.addAll(effects);
            return this;
        }

        public ChainOfEffects build() {
            return new ChainOfEffects(new ArrayList<>(effects));
        }
    }
}