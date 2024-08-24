package com.project.paradoxplatformer.utils.effect;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.ChainOfEffects;
import com.project.paradoxplatformer.utils.collision.ChainOfEffects.Builder;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.effect.api.Effect;

public class EffectHandler {
        private final Map<CollisionType, ChainOfEffects> typeEffectsMap = new EnumMap<>(CollisionType.class);
        private final Map<CollisionType, Map<CollidableGameObject, ChainOfEffects>> objectEffectsMap = new EnumMap<>(
                        CollisionType.class);

        // Register effects for a specific collision type that applies to all objects of
        // that type
        public void addCollisionEffectsForType(CollisionType type, Supplier<Effect> effectSupplier) {
                ChainOfEffects chain = ChainOfEffects.builder().addEffect(effectSupplier.get()).build();
                typeEffectsMap.put(type, chain);
        }

        // Register effects for a specific collision type and CollidableGameObject
        public void addCollisionEffectsForObject(CollisionType type, CollidableGameObject collidableGameObject,
                        Supplier<Effect> effectSupplier) {
                ChainOfEffects chain = ChainOfEffects.builder().addEffect(effectSupplier.get()).build();
                objectEffectsMap.computeIfAbsent(type, k -> new HashMap<>()).put(collidableGameObject, chain);
        }

        // Apply effects for a specific CollidableGameObject
        public void applyEffects(CollidableGameObject source, CollidableGameObject target) {
                // Apply effects for the collision type, regardless of the specific object
                // instance
                Optional.ofNullable(typeEffectsMap.get(source.getCollisionType()))
                                .ifPresent(chain -> chain.applyEffectsSequentially(Optional.of(target)));

                // Apply effects specific to the source object
                Optional.ofNullable(objectEffectsMap.get(source.getCollisionType()))
                                .map(objectMap -> objectMap.get(source))
                                .ifPresent(chain -> chain.applyEffectsSequentially(Optional.of(target)));
        }

        // Get all effects for a specific CollidableGameObject
        public ChainOfEffects getAllEffects(CollidableGameObject collidableGameObject) {
                Builder combinedChain = ChainOfEffects.builder();

                // Add effects for the collision type
                Optional.ofNullable(typeEffectsMap.get(collidableGameObject.getCollisionType()))
                                .ifPresent(chain -> combinedChain.addEffects(chain.getEffects()));

                // Add effects specific to the object
                Optional.ofNullable(objectEffectsMap.get(collidableGameObject.getCollisionType()))
                                .map(objectMap -> objectMap.get(collidableGameObject))
                                .ifPresent(chain -> combinedChain.addEffects(chain.getEffects()));

                return combinedChain.build();
        }

        // Add a single effect to a specific CollidableGameObject
        public void addSingleEffect(CollisionType type, CollidableGameObject collidableGameObject,
                        Supplier<Effect> effectSupplier) {
                ChainOfEffects chain = ChainOfEffects.builder().addEffect(effectSupplier.get()).build();
                objectEffectsMap.computeIfAbsent(type, k -> new HashMap<>()).put(collidableGameObject, chain);
        }

        // Create default ChainOfEffects for specific cases (factory method)
        public ChainOfEffects createDefaultChainOfEffects(Supplier<Effect>... effectSuppliers) {
                ChainOfEffects.Builder builder = ChainOfEffects.builder();
                for (Supplier<Effect> supplier : effectSuppliers) {
                        builder.addEffect(supplier.get());
                }
                return builder.build();
        }

        // Factory method for creating default collision effects for an object
        public static EffectHandler createDefaultEffectHandler() {
                EffectHandler handler = new EffectHandler();

                handler.addCollisionEffectsForType(CollisionType.OBSTACLE, () -> new SoundEffect("HITS AN OBSTACLE"));
                handler.addCollisionEffectsForType(CollisionType.TRIGGER, () -> new SoundEffect("HITS AN TRIGGER"));
                return handler;
        }
}
