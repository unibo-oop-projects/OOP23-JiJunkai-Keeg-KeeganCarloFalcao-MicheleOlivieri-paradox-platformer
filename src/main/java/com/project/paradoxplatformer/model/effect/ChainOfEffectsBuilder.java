package com.project.paradoxplatformer.model.effect;

import java.util.ArrayList;
import java.util.List;

import com.project.paradoxplatformer.model.effect.api.Effect;

/**
 * Builder class for constructing ChainOfEffects instances.
 */
public class ChainOfEffectsBuilder {
    private final List<Effect> effects = new ArrayList<>();

    /**
     * Adds an effect to the chain.
     *
     * @param effect the effect to add
     * @return the builder instance for chaining
     */
    public ChainOfEffectsBuilder addEffect(final Effect effect) {
        effects.add(effect);
        return this;
    }

    /**
     * Adds multiple effects to the chain.
     *
     * @param effects the list of effects to add
     * @return the builder instance for chaining
     */
    public ChainOfEffectsBuilder addEffects(final List<Effect> effects) {
        this.effects.addAll(effects);
        return this;
    }

    /**
     * Builds and returns a ChainOfEffects instance.
     *
     * @return a new ChainOfEffects instance with the added effects
     */
    public ChainOfEffects build() {
        return new ChainOfEffects(effects);
    }

    /**
     * Static factory method for creating a new ChainOfEffectsBuilder.
     *
     * @return a new builder instance
     */
    public static ChainOfEffectsBuilder builder() {
        return new ChainOfEffectsBuilder();
    }
}
