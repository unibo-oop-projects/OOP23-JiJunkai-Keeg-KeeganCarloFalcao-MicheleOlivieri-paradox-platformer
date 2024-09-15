package com.project.paradoxplatformer.model.effect.managers;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.project.paradoxplatformer.model.effect.ChainOfEffects;
import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

public class ObjectEffectsManager {

    private final Map<CollisionType, Map<CollidableGameObject, ChainOfEffects>> objectEffectsMap = new EnumMap<>(
            CollisionType.class);

    public void addEffects(CollisionType type, CollidableGameObject object, Supplier<Effect> effectSupplier) {
        objectEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                .merge(object,
                        ChainOfEffects.builder().addEffect(effectSupplier.get()).build(),
                        this::mergeChains);
    }

    public void addEffects(CollisionType type, CollidableGameObject object, ChainOfEffects newChain) {
        objectEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                .merge(object,
                        newChain,
                        this::mergeChains);
    }

    public void replaceEffects(CollisionType type, CollidableGameObject object, ChainOfEffects newChain) {
        objectEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                .put(object, newChain);
    }

    public ChainOfEffects getEffects(CollisionType type, CollidableGameObject object) {
        return objectEffectsMap.getOrDefault(type, new HashMap<>()).getOrDefault(object,
                ChainOfEffects.builder().build());
    }

    public void clearEffects(CollisionType type, CollidableGameObject object) {
        Map<CollidableGameObject, ChainOfEffects> objectMap = objectEffectsMap.get(type);
        if (objectMap != null) {
            objectMap.remove(object);
            if (objectMap.isEmpty()) {
                objectEffectsMap.remove(type);
            }
        }
    }

    private ChainOfEffects mergeChains(ChainOfEffects existingChain, ChainOfEffects newChain) {
        return ChainOfEffects.builder()
                .addEffects(existingChain.getEffects())
                .addEffects(newChain.getEffects())
                .build();
    }
}
