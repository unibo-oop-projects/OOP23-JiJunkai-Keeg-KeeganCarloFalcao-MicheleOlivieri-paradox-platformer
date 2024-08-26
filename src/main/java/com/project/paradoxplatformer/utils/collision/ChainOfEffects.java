package com.project.paradoxplatformer.utils.collision;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.effect.api.Effect;

/**
 * Represents a chain of effects to be applied sequentially to a target object.
 * This class is typically used in collision scenarios where multiple effects
 * need to be applied to involved objects.
 */
public class ChainOfEffects {
    private final List<Effect> effects;

    private ChainOfEffects(List<Effect> effects) {
        this.effects = effects;
    }

    /**
     * Applies the chain of effects to the specified target object asynchronously.
     * The effects are applied sequentially in the order they were added.
     *
     * @param target the optional target object to apply effects to
     * @return a CompletableFuture that completes when all effects have been applied
     */
    public CompletableFuture<Void> applyEffectsSequentially(Optional<? extends CollidableGameObject> target) {
        CompletableFuture<Void> future = CompletableFuture.completedFuture(null);
        for (Effect effect : effects) {
            future = future.thenCompose(v -> effect.apply(target));
        }
        return future;
    }

    /**
     * Returns the list of effects in this chain.
     *
     * @return the list of effects
     */
    public List<Effect> getEffects() {
        return effects;
    }

    /**
     * Creates a builder for constructing ChainOfEffects instances.
     *
     * @return a new ChainOfEffects builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder class for constructing ChainOfEffects instances.
     */
    public static class Builder {
        private final List<Effect> effects = new ArrayList<>();

        /**
         * Adds an effect to the chain.
         *
         * @param effect the effect to add
         * @return the builder for chaining
         */
        public Builder addEffect(Effect effect) {
            effects.add(effect);
            return this;
        }

        /**
         * Adds a list of effects to the chain.
         *
         * @param effects the list of effects to add
         * @return the builder for chaining
         */
        public Builder addEffects(List<Effect> effects) {
            this.effects.addAll(effects);
            return this;
        }

        /**
         * Builds the ChainOfEffects instance.
         *
         * @return the constructed ChainOfEffects instance
         */
        public ChainOfEffects build() {
            return new ChainOfEffects(new ArrayList<>(effects));
        }
    }
}
