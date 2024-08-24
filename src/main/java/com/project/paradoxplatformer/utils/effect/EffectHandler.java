package com.project.paradoxplatformer.utils.effect;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.ChainOfEffects;
import com.project.paradoxplatformer.utils.collision.ChainOfEffects.Builder;
import com.project.paradoxplatformer.utils.collision.CollisionIdentifier;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.effect.api.Effect;
import com.project.paradoxplatformer.utils.sound.SoundPathUtil;
import com.project.paradoxplatformer.utils.sound.SoundType;

public class EffectHandler {
        private final Map<CollisionType, ChainOfEffects> typeEffectsMap = new EnumMap<>(CollisionType.class);
        private final Map<CollisionType, Map<CollidableGameObject, ChainOfEffects>> objectEffectsMap = new EnumMap<>(
                        CollisionType.class);

        private final Set<CollisionIdentifier> currentCollisions = new HashSet<>();

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
        public CompletableFuture<Void> applyEffects(CollidableGameObject source, CollidableGameObject target) {
                System.out.println("Applying effects for collision between " + source + " and " + target);

                CollisionIdentifier collisionId = new CollisionIdentifier(source, target);

                // Check if the collision is already being processed
                if (currentCollisions.contains(collisionId)) {
                        System.out.println("Collision already processed, skipping.");
                        return CompletableFuture.completedFuture(null);
                }

                // Add to currently processing collisions
                currentCollisions.add(collisionId);

                // Process effects
                CompletableFuture<Void> typeEffectsFuture = CompletableFuture.completedFuture(null);
                CompletableFuture<Void> objectEffectsFuture = CompletableFuture.completedFuture(null);

                if (typeEffectsMap.containsKey(target.getCollisionType())) {
                        ChainOfEffects typeEffectsChain = typeEffectsMap.get(target.getCollisionType());
                        System.out.println(
                                        "Applying type-based effects for collision type: " + target.getCollisionType());
                        typeEffectsFuture = typeEffectsChain.applyEffectsSequentially(Optional.of(target));
                }

                if (objectEffectsMap.containsKey(target.getCollisionType()) &&
                                objectEffectsMap.get(target.getCollisionType()).containsKey(target)) {
                        ChainOfEffects objectEffectsChain = objectEffectsMap.get(target.getCollisionType()).get(target);
                        System.out.println("Applying object-specific effects for object: " + target);
                        objectEffectsFuture = objectEffectsChain.applyEffectsSequentially(Optional.of(target));
                }

                // Remove the collision after effects are applied
                return CompletableFuture.allOf(typeEffectsFuture, objectEffectsFuture)
                                .thenRun(() -> currentCollisions.remove(collisionId));
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

        public ChainOfEffects createDefaultChainOfEffects(List<Supplier<Effect>> effectSuppliers) {
                ChainOfEffects.Builder builder = ChainOfEffects.builder();
                for (Supplier<Effect> supplier : effectSuppliers) {
                        builder.addEffect(supplier.get());
                }
                return builder.build();
        }

        // Factory method for creating default collision effects for an object
        public static EffectHandler createDefaultEffectHandler() {
                EffectHandler handler = new EffectHandler();

                handler.addCollisionEffectsForType(CollisionType.OBSTACLE,
                                () -> new NoOpEffect());
                handler.addCollisionEffectsForType(CollisionType.TRIGGER,
                                () -> new NoOpEffect());
                handler.addCollisionEffectsForType(CollisionType.OBSTACLE,
                                () -> new SoundEffect(SoundPathUtil.getPathForSound(SoundType.OBSTACLE_HIT)));
                handler.addCollisionEffectsForType(CollisionType.TRIGGER,
                                () -> new SoundEffect(SoundPathUtil.getPathForSound(SoundType.OBSTACLE_HIT)));
                return handler;
        }
}
