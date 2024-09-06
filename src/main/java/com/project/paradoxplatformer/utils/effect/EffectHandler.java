package com.project.paradoxplatformer.utils.effect;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.ChainOfEffects;
import com.project.paradoxplatformer.utils.collision.ChainOfEffects.Builder;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.effect.api.Effect;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.sound.SoundType;

public class EffectHandler {
        private final Map<CollisionType, ChainOfEffects> typeEffectsMap = new EnumMap<>(CollisionType.class);
        private final Map<CollisionType, Map<CollidableGameObject, ChainOfEffects>> objectEffectsMap = new EnumMap<>(
                        CollisionType.class);

        // Register effects for a specific collision type that applies to all objects of
        // that type
        public void addCollisionEffectsForType(CollisionType type, Supplier<Effect> effectSupplier) {
                typeEffectsMap.merge(type,
                                ChainOfEffects.builder().addEffect(effectSupplier.get()).build(),
                                (existingChain, newChain) -> ChainOfEffects.builder()
                                                .addEffects(existingChain.getEffects())
                                                .addEffects(newChain.getEffects())
                                                .build());
        }

        // Register effects for a specific collision type that applies to all objects of
        // that type
        public void addCollisionEffectsForType(CollisionType type, ChainOfEffects newChain) {
                typeEffectsMap.merge(type,
                                newChain,
                                (existingChain, toAddChain) -> ChainOfEffects.builder()
                                                .addEffects(existingChain.getEffects())
                                                .addEffects(toAddChain.getEffects())
                                                .build());
        }

        // Register effects for a specific collision type and CollidableGameObject
        public void addCollisionEffectsForObject(CollisionType type, CollidableGameObject collidableGameObject,
                        Supplier<Effect> effectSupplier) {
                objectEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                                .merge(collidableGameObject,
                                                ChainOfEffects.builder().addEffect(effectSupplier.get()).build(),
                                                (existingChain, newChain) -> ChainOfEffects.builder()
                                                                .addEffects(existingChain.getEffects())
                                                                .addEffects(newChain.getEffects())
                                                                .build());
        }

        // Register effects for a specific collision type and CollidableGameObject
        public void addCollisionEffectsForObject(CollisionType type, CollidableGameObject collidableGameObject,
                        ChainOfEffects newChain) {
                objectEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                                .merge(collidableGameObject,
                                                newChain,
                                                (existingChain, toAddChain) -> ChainOfEffects.builder()
                                                                .addEffects(existingChain.getEffects())
                                                                .addEffects(toAddChain.getEffects())
                                                                .build());
        }

        // Apply effects for a specific CollidableGameObject
        public CompletableFuture<Void> applyEffects(CollidableGameObject source, CollidableGameObject target) {
                // System.out.println("Applying effects for collision between " + source + " and
                // " + target);

                CompletableFuture<Void> typeEffectsFuture = CompletableFuture.completedFuture(null);
                CompletableFuture<Void> objectEffectsFuture = CompletableFuture.completedFuture(null);

                // Apply effects for the target
                if (typeEffectsMap.containsKey(target.getCollisionType())) {
                        ChainOfEffects typeEffectsChain = typeEffectsMap.get(target.getCollisionType());
                        System.out.println(
                                        "Applying type-based effects for collision type: " + target.getCollisionType());
                        typeEffectsFuture = typeEffectsChain.applyEffectsSequentially(Optional.of(source),
                                        Optional.of(target));
                }

                if (objectEffectsMap.containsKey(target.getCollisionType()) &&
                                objectEffectsMap.get(target.getCollisionType()).containsKey(target)) {
                        ChainOfEffects objectEffectsChain = objectEffectsMap.get(target.getCollisionType()).get(target);
                        System.out.println("Applying object-specific effects for object: " + target);
                        objectEffectsFuture = objectEffectsChain.applyEffectsSequentially(Optional.of(source),
                                        Optional.of(target));
                }

                // Combine all futures
                return CompletableFuture.allOf(typeEffectsFuture, objectEffectsFuture);
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

        public void reset(CollidableGameObject object, CollisionType type) {
                // Recreate effects in objectEffectsMap
                objectEffectsMap.computeIfPresent(type, (t, map) -> {
                        map.computeIfPresent(object, (obj, chain) -> {
                                List<Effect> recreatedEffects = chain.getEffects().stream()
                                                .map(e -> e.recreate())
                                                .filter(e -> e != null)
                                                .toList();
                                return ChainOfEffects.builder().addEffects(recreatedEffects).build();
                        });
                        return map;
                });

                // Recreate effects in typeEffectsMap
                typeEffectsMap.compute(type, (t, chain) -> chain == null ? null
                                : ChainOfEffects.builder()
                                                .addEffects(chain.getEffects().stream()
                                                                .map(e -> e.recreate())
                                                                .filter(e -> e != null)
                                                                .toList())
                                                .build());
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

                // handler.addCollisionEffectsForType(CollisionType.OBSTACLE,
                //                 () -> new NoOpEffect());
                // handler.addCollisionEffectsForType(CollisionType.OBSTACLE,
                //                 () -> new SoundEffect(SoundPathUtil.getPathForSound(SoundType.OBSTACLE_HIT)));

                ChainOfEffects chain = ChainOfEffects.builder()
                                .addEffect(new NoOpEffect())
                                .addEffect(new SoundEffect(SoundType.OBSTACLE_HIT))
                                .addEffect(new TransportEffect(new Coord2D(100, 100), false))
                                .build();

                handler.addCollisionEffectsForType(CollisionType.TRIGGER, chain);

                handler.addCollisionEffectsForType(CollisionType.DEATH_OBS,
                                () -> new DeathEffect());

                handler.addCollisionEffectsForType(CollisionType.SPRINGS,
                                () -> new SpringEffect());

                handler.addCollisionEffectsForType(CollisionType.COLLECTING,
                        new EffectFactoryImpl()::collectingEffect);


                handler.addCollisionEffectsForType(CollisionType.WALLS,
                        new EffectFactoryImpl()::stoppingEffect);

                return handler;
        }
}
