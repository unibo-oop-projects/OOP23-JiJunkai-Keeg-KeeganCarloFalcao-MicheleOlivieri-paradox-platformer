package com.project.paradoxplatformer.model.effect;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

/**
 * Represents a chain of effects to be applied sequentially to a target object.
 * This class is typically used in collision scenarios where multiple effects
 * need to be applied to involved objects.
 */
public class ChainOfEffects {
    private final List<Effect> effects;

    public ChainOfEffects(final List<Effect> effects) {
        this.effects = List.copyOf(effects); // Defensive copy for immutability
    }

    /**
     * Applies the chain of effects to the specified target object asynchronously.
     * The effects are applied sequentially in the order they were added.
     *
     * @param target the optional target object to apply effects to
     * @return a CompletableFuture that completes when all effects have been applied
     */
    public CompletableFuture<Void> applyToTarget(final Optional<? extends CollidableGameObject> target) {
        return applySequentially(target, Optional.empty());
    }

    /**
     * Applies the chain of effects to both target and self objects asynchronously.
     * The effects are applied sequentially in the order they were added.
     *
     * @param target the optional target object to apply effects to
     * @param self   the optional self object to apply effects to
     * @return a CompletableFuture that completes when all effects have been applied
     */
    public CompletableFuture<Void> applyToBoth(final Optional<? extends CollidableGameObject> target,
            final Optional<? extends CollidableGameObject> self) {
        return applySequentially(target, self);
    }

    /**
     * Helper method to apply effects sequentially to target and self.
     *
     * @param target the optional target object
     * @param self   the optional self object
     * @return a CompletableFuture that completes when all effects have been applied
     */
    private CompletableFuture<Void> applySequentially(final Optional<? extends CollidableGameObject> target,
            final Optional<? extends CollidableGameObject> self) {
        if (effects.isEmpty()) {
            return CompletableFuture.completedFuture(null);
        }

        CompletableFuture<Void> future = CompletableFuture.completedFuture(null);
        for (final Effect effect : effects) {
            future = future.thenCompose(v -> effect.apply(target, self));
        }
        return future;
    }

    /**
     * Creates a ChainOfEffects instance from a list of effect suppliers.
     * Each supplier is used to create an effect, and all effects are added to the
     * chain.
     *
     * @param effectSuppliers the list of suppliers that provide effects
     * @return a ChainOfEffects instance containing the created effects
     */
    public static ChainOfEffects create(final List<Supplier<Effect>> effectSuppliers) {
        List<Effect> effects = effectSuppliers.stream()
                .map(Supplier::get)
                .toList();
        return new ChainOfEffects(effects);
    }

    /**
     * Returns the list of effects in this chain.
     *
     * @return an immutable list of effects
     */
    public List<Effect> getEffects() {
        return effects;
    }
}
