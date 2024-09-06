package com.project.paradoxplatformer.utils.effect;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.ChainOfEffects;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.effect.api.Effect;
import com.project.paradoxplatformer.utils.effect.managers.ObjectEffectsManager;
import com.project.paradoxplatformer.utils.effect.managers.TypeEffectsManager;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.sound.SoundType;

public class EffectHandler {

        private final TypeEffectsManager typeEffectsManager = new TypeEffectsManager();
        private final ObjectEffectsManager objectEffectsManager = new ObjectEffectsManager();

        public void addCollisionEffectsForType(CollisionType type, Supplier<Effect> effectSupplier) {
                typeEffectsManager.addEffects(type, effectSupplier);
        }

        public void addCollisionEffectsForType(CollisionType type, ChainOfEffects newChain) {
                typeEffectsManager.addEffects(type, newChain);
        }

        public void addCollisionEffectsForObject(CollisionType type, CollidableGameObject object,
                        Supplier<Effect> effectSupplier) {
                objectEffectsManager.addEffects(type, object, effectSupplier);
        }

        public void addCollisionEffectsForObject(CollisionType type, CollidableGameObject object,
                        ChainOfEffects newChain) {
                objectEffectsManager.addEffects(type, object, newChain);
        }

        public CompletableFuture<Void> applyEffects(CollidableGameObject source, CollidableGameObject target) {

                System.out.println("Source : " + source + " -------" + "Target: " + target);

                CompletableFuture<Void> typeEffectsFuture = applyEffects(
                                typeEffectsManager.getEffects(target.getCollisionType()), source, target);

                CompletableFuture<Void> objectEffectsFuture = applyEffects(
                                objectEffectsManager.getEffects(target.getCollisionType(), target), source, target);

                return CompletableFuture.allOf(typeEffectsFuture, objectEffectsFuture);
        }

        private CompletableFuture<Void> applyEffects(ChainOfEffects effectsChain, CollidableGameObject source,
                        CollidableGameObject target) {
                return effectsChain.applyEffectsSequentially(Optional.of(source), Optional.of(target));
        }

        public ChainOfEffects getAllEffects(CollidableGameObject object) {
                ChainOfEffects.Builder combinedChain = ChainOfEffects.builder();

                combinedChain.addEffects(typeEffectsManager.getEffects(object.getCollisionType()).getEffects());

                combinedChain.addEffects(
                                objectEffectsManager.getEffects(object.getCollisionType(), object).getEffects());
                return combinedChain.build();
        }

        public void reset(CollidableGameObject object, CollisionType type) {
                objectEffectsManager.addEffects(type, object,
                                ChainOfEffects.builder().addEffects(recreateIfPossible(
                                                objectEffectsManager.getEffects(type, object).getEffects())).build());

                typeEffectsManager.replaceEffects(type,
                                ChainOfEffects.builder().addEffects(recreateIfPossible(
                                                typeEffectsManager.getEffects(type).getEffects())).build());
        }

        private List<Effect> recreateIfPossible(List<? extends Effect> effects) {
                return effects.stream()
                                .map(this::tryRecreate)
                                .flatMap(Optional::stream)
                                .toList();
        }

        private Optional<Effect> tryRecreate(Effect effect) {
                try {
                        return Optional.ofNullable((Effect) effect.getClass().getMethod("recreate").invoke(effect));
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        return Optional.empty();
                }
        }

        public ChainOfEffects createDefaultChainOfEffects(List<Supplier<Effect>> effectSuppliers) {
                return ChainOfEffects.builder()
                                .addEffects(effectSuppliers.stream().map(Supplier::get).toList())
                                .build();
        }

        public static EffectHandler createDefaultEffectHandler() {
                EffectHandler handler = new EffectHandler();

                ChainOfEffects chain = ChainOfEffects.builder()
                                .addEffect(new NoOpEffect())
                                .addEffect(new SoundEffect(SoundType.OBSTACLE_HIT))
                                .addEffect(new TransportEffect(new Coord2D(100, 100), false))
                                .build();

                handler.addCollisionEffectsForType(CollisionType.TRIGGER, chain);
                handler.addCollisionEffectsForType(CollisionType.DEATH_OBS, DeathEffect::new);
                handler.addCollisionEffectsForType(CollisionType.SPRINGS, SpringEffect::new);
                handler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
                handler.addCollisionEffectsForType(CollisionType.WALLS, new EffectFactoryImpl()::stoppingEffect);

                return handler;
        }
}
