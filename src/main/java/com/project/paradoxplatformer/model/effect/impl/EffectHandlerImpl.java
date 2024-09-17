package com.project.paradoxplatformer.model.effect.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.model.effect.api.EffectHandler;
import com.project.paradoxplatformer.model.effect.managers.ChainOfEffects;
import com.project.paradoxplatformer.model.effect.managers.ChainOfEffectsBuilder;
import com.project.paradoxplatformer.model.effect.managers.ObjectEffectsManager;
import com.project.paradoxplatformer.model.effect.managers.TypeEffectsManager;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

/**
 * Manages the application and handling of effects in the game.
 * This class facilitates adding, applying, and resetting effects for game
 * objects
 * based on their collision types and specific instances.
 */
public final class EffectHandlerImpl implements EffectHandler {

        private final TypeEffectsManager typeEffectsManager = new TypeEffectsManager();
        private final ObjectEffectsManager objectEffectsManager = new ObjectEffectsManager();

        /**
         * Adds a collision effect for a specific collision type.
         *
         * @param type           the collision type
         * @param effectSupplier the supplier for the effect to be added
         */
        @Override
        public void addCollisionEffectsForType(final CollisionType type, final Supplier<Effect> effectSupplier) {
                typeEffectsManager.addEffects(type, effectSupplier);
        }

        /**
         * Adds a chain of effects for a specific collision type.
         *
         * @param type     the collision type
         * @param newChain the chain of effects to be added
         */
        @Override
        public void addCollisionEffectsForType(final CollisionType type, final ChainOfEffects newChain) {
                typeEffectsManager.addEffects(type, newChain);
        }

        /**
         * Adds a collision effect for a specific collision type and game object.
         *
         * @param type           the collision type
         * @param object         the game object
         * @param effectSupplier the supplier for the effect to be added
         */
        @Override
        public void addCollisionEffectsForObject(final CollisionType type, final CollidableGameObject object,
                        final Supplier<Effect> effectSupplier) {
                objectEffectsManager.addEffects(type, object, effectSupplier);
        }

        /**
         * Adds a chain of effects for a specific collision type and game object.
         *
         * @param type     the collision type
         * @param object   the game object
         * @param newChain the chain of effects to be added
         */
        @Override
        public void addCollisionEffectsForObject(final CollisionType type, final CollidableGameObject object,
                        final ChainOfEffects newChain) {
                objectEffectsManager.addEffects(type, object, newChain);
        }

        /**
         * Applies effects to the target object based on its collision type and specific
         * instance.
         *
         * @param source the source game object
         * @param target the target game object
         * @return a CompletableFuture that completes when all effects have been applied
         */
        @Override
        public CompletableFuture<Void> applyEffects(final CollidableGameObject source,
                        final CollidableGameObject target) {

                // Apply type-based effects
                final CompletableFuture<Void> typeEffectsFuture = applyEffects(
                                typeEffectsManager.getEffects(target.getCollisionType()), source, target);

                // Apply object-specific effects
                final CompletableFuture<Void> objectEffectsFuture = applyEffects(
                                objectEffectsManager.getEffects(target.getCollisionType(), target), source, target);

                // Return a future that completes when both type and object effects are applied
                return CompletableFuture.allOf(typeEffectsFuture, objectEffectsFuture);
        }

        /**
         * Applies a chain of effects sequentially to the source and target objects.
         *
         * @param effectsChain the chain of effects to be applied
         * @param source       the source game object
         * @param target       the target game object
         * @return a CompletableFuture that completes when all effects in the chain have
         *         been applied
         */
        private CompletableFuture<Void> applyEffects(final ChainOfEffects effectsChain,
                        final CollidableGameObject source,
                        final CollidableGameObject target) {
                return effectsChain.applyToBoth(Optional.of(source), Optional.of(target));
        }

        /**
         * Retrieves all effects associated with a specific game object, combining both
         * type and object-specific effects.
         *
         * @param object the game object
         * @return a ChainOfEffects instance containing all combined effects
         */
        @Override
        public ChainOfEffects getAllEffects(final CollidableGameObject object) {
                final ChainOfEffectsBuilder combinedChain = ChainOfEffectsBuilder.builder();

                // Add type-based effects
                combinedChain.addEffects(typeEffectsManager.getEffects(object.getCollisionType()).getEffects());

                // Add object-specific effects
                combinedChain.addEffects(
                                objectEffectsManager.getEffects(object.getCollisionType(), object).getEffects());

                return combinedChain.build();
        }

        /**
         * Resets the effects associated with a specific game object and collision type,
         * recreating effects if possible.
         *
         * @param object the game object
         * @param type   the collision type
         */
        @Override
        public void reset(final CollidableGameObject object, final CollisionType type) {
                objectEffectsManager.replaceEffects(type, object,
                                ChainOfEffectsBuilder.builder().addEffects(recreateIfPossible(
                                                objectEffectsManager.getEffects(type, object).getEffects())).build());

                typeEffectsManager.replaceEffects(type,
                                ChainOfEffectsBuilder.builder().addEffects(recreateIfPossible(
                                                typeEffectsManager.getEffects(type).getEffects())).build());
        }

        /**
         * Attempts to recreate effects if they support recreation, returning a list of
         * recreated effects.
         *
         * @param effects the list of effects to attempt to recreate
         * @return a list of recreated effects
         */
        private List<Effect> recreateIfPossible(final List<? extends Effect> effects) {
                return effects.stream()
                                .map(this::tryRecreate)
                                .flatMap(Optional::stream)
                                .toList();
        }

        /**
         * Attempts to invoke the "recreate" method on an effect, if it exists.
         *
         * @param effect the effect to attempt to recreate
         * @return an Optional containing the recreated effect, or an empty Optional if
         *         recreation failed
         */
        private Optional<Effect> tryRecreate(final Effect effect) {
                try {
                        return Optional.ofNullable((Effect) effect.getClass().getMethod("recreate").invoke(effect));
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        return Optional.empty();
                }
        }
}
