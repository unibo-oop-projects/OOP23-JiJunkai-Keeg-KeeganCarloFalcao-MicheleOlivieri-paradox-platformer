package com.project.paradoxplatformer.utils.effect.managers;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import com.project.paradoxplatformer.utils.collision.ChainOfEffects;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.effect.api.Effect;

public class TypeEffectsManager {

    private final Map<CollisionType, ChainOfEffects> typeEffectsMap = new EnumMap<>(CollisionType.class);

    public void addEffects(CollisionType type, Supplier<Effect> effectSupplier) {
        typeEffectsMap.merge(type,
                ChainOfEffects.builder().addEffect(effectSupplier.get()).build(),
                (existingChain, newChain) -> ChainOfEffects.builder()
                        .addEffects(existingChain.getEffects())
                        .addEffects(newChain.getEffects())
                        .build());
    }

    public void addEffects(CollisionType type, ChainOfEffects newChain) {
        typeEffectsMap.merge(type,
                newChain,
                (existingChain, toAddChain) -> ChainOfEffects.builder()
                        .addEffects(existingChain.getEffects())
                        .addEffects(toAddChain.getEffects())
                        .build());
    }

    public void replaceEffects(CollisionType type, ChainOfEffects newChain) {
        typeEffectsMap.put(type, newChain);
    }

    public ChainOfEffects getEffects(CollisionType type) {
        return typeEffectsMap.getOrDefault(type, ChainOfEffects.builder().build());
    }

    public void clearEffects(CollisionType type) {
        typeEffectsMap.remove(type);
    }
}
