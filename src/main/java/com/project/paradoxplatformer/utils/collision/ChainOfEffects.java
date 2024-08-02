package com.project.paradoxplatformer.utils.collision;

import java.util.ArrayList;
import java.util.List;

import com.project.paradoxplatformer.model.trigger.api.Effect;

import java.util.Optional;

public class ChainOfEffects {
    private final List<Effect> effects;

    private ChainOfEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public void applyEffects(Optional<Object> target) {
        effects.forEach(effect -> effect.apply(target));
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