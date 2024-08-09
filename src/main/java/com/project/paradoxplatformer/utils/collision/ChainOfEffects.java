package com.project.paradoxplatformer.utils.collision;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.paradoxplatformer.utils.collision.api.Collidable;
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
     * Applies the chain of effects to the specified target object.
     * The effects are applied sequentially in the order they were added.
     *
     * @param target the optional target object to apply effects to
     */
    public void applyEffectsSequentially(Optional<? extends Collidable> target) {
        for (Effect effect : effects) {
            effect.apply(target);
        }
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
