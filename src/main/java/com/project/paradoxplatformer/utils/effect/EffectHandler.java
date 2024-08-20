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
        private final Map<CollisionType, List<ChainOfEffects>> typeEffectsMap = new EnumMap<>(CollisionType.class);
        private final Map<CollisionType, Map<CollidableGameObject, List<ChainOfEffects>>> objectEffectsMap = new EnumMap<>(
                        CollisionType.class);

        // Register effects for a specific collision type that applies to all objects of
        // that type
        public void addCollisionEffectsForType(CollisionType type, List<Supplier<Effect>> effectSuppliers) {
                List<Effect> effects = new ArrayList<>();
                effectSuppliers.forEach(supplier -> effects.add(supplier.get()));
                ChainOfEffects chain = ChainOfEffects.builder().addEffects(effects).build();
                typeEffectsMap.computeIfAbsent(type, k -> new ArrayList<>()).add(chain);
        }

        // Register effects for a specific collision type and CollidableGameObject
        public void addCollisionEffectsForObject(CollisionType type, CollidableGameObject collidableGameObject,
                        List<Supplier<Effect>> effectSuppliers) {
                List<Effect> effects = new ArrayList<>();
                effectSuppliers.forEach(supplier -> effects.add(supplier.get()));
                ChainOfEffects chain = ChainOfEffects.builder().addEffects(effects).build();
                objectEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                                .computeIfAbsent(collidableGameObject, k -> new ArrayList<>()).add(chain);
        }

        // Apply effects for a specific CollidableGameObject
        public void applyEffects(CollidableGameObject source, CollidableGameObject target) {
                // Apply effects for the collision type, regardless of the specific object
                // instance
                typeEffectsMap.getOrDefault(source.getCollisionType(), List.of())
                                .forEach(chain -> chain.applyEffectsSequentially(Optional.of(target)));

                // Apply effects specific to the source object
                objectEffectsMap.getOrDefault(source.getCollisionType(), Map.of())
                                .getOrDefault(source, List.of())
                                .forEach(chain -> chain.applyEffectsSequentially(Optional.of(target)));
        }

        // Get all effects for a specific CollidableGameObject
        public List<Effect> getAllEffects(CollidableGameObject collidableGameObject) {
                List<Effect> effects = new ArrayList<>();

                // Add effects for the collision type
                typeEffectsMap.getOrDefault(collidableGameObject.getCollisionType(), List.of())
                                .forEach(chain -> effects.addAll(chain.getEffects()));

                // Add effects specific to the object
                objectEffectsMap.getOrDefault(collidableGameObject.getCollisionType(), Map.of())
                                .getOrDefault(collidableGameObject, List.of())
                                .forEach(chain -> effects.addAll(chain.getEffects()));

                return effects;
        }

        // Add a single effect to a specific CollidableGameObject
        public void addSingleEffect(CollisionType type, CollidableGameObject collidableGameObject,
                        Supplier<Effect> effectSupplier) {
                ChainOfEffects chain = ChainOfEffects.builder().addEffect(effectSupplier.get()).build();
                objectEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                                .computeIfAbsent(collidableGameObject, k -> new ArrayList<>()).add(chain);
        }
}
