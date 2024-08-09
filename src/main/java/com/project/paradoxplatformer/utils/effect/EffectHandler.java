package com.project.paradoxplatformer.utils.effect;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.project.paradoxplatformer.utils.collision.ChainOfEffects;
import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.effect.api.Effect;

public class EffectHandler {
        private final Map<CollisionType, Map<Collidable, List<ChainOfEffects>>> collisionEffectsMap = new EnumMap<>(
                        CollisionType.class);

        // Register effects for a specific collision type and Collidable
        public void addCollisionEffects(CollisionType type, Collidable Collidable,
                        List<Supplier<Effect>> effectSuppliers) {
                ChainOfEffects.Builder builder = ChainOfEffects.builder();
                effectSuppliers.forEach(supplier -> builder.addEffect(supplier.get()));
                ChainOfEffects chain = builder.build();
                collisionEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                                .computeIfAbsent(Collidable, k -> new ArrayList<>()).add(chain);
        }

        // Apply effects for a specific Collidable
        public void applyEffects(Collidable source, Collidable target) {
                collisionEffectsMap.getOrDefault(source.getCollisionType(), Map.of())
                                .getOrDefault(source, List.of())
                                .forEach(chain -> chain.applyEffectsSequentially(Optional.of(target)));
        }
}
