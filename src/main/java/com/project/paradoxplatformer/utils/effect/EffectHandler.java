package com.project.paradoxplatformer.utils.effect;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.ChainOfEffects;

import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.effect.api.Effect;

public class EffectHandler {
        private final Map<CollisionType, Map<CollidableGameObject, List<ChainOfEffects>>> collisionEffectsMap = new EnumMap<>(
                        CollisionType.class);

        // Register effects for a specific collision type and CollidableGameObject
        public void addCollisionEffects(CollisionType type, CollidableGameObject collidableGameObject,
                        List<Supplier<Effect>> effectSuppliers) {
                ChainOfEffects.Builder builder = ChainOfEffects.builder();
                effectSuppliers.forEach(supplier -> builder.addEffect(supplier.get()));
                ChainOfEffects chain = builder.build();
                collisionEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                                .computeIfAbsent(collidableGameObject, k -> new ArrayList<>()).add(chain);
        }

        // Apply effects for a specific CollidableGameObject
        public void applyEffects(CollidableGameObject source, CollidableGameObject target) {
                collisionEffectsMap.getOrDefault(source.getCollisionType(), Map.of())
                                .getOrDefault(source, List.of())
                                .forEach(chain -> chain.applyEffectsSequentially(Optional.of(target)));
        }
}
