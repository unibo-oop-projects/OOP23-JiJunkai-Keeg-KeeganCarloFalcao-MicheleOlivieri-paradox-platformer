package com.project.paradoxplatformer.model.effect;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Abstract base class for effects. Provides a default implementation
 * for applying effects to both target and self.
 */
public abstract class AbstractEffect implements Effect {

    @Override
    public CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target,
            Optional<? extends CollidableGameObject> self) {
        // Default implementation can be overridden by subclasses
        CompletableFuture<Void> targetFuture = applyToTarget(target);
        CompletableFuture<Void> selfFuture = applyToSelf(self);

        return CompletableFuture.allOf(targetFuture, selfFuture);
    }

    /**
     * Applies the effect to the target object if present.
     *
     * @param target the optional target object
     * @return a CompletableFuture representing the completion of the effect
     *         application
     */
    protected CompletableFuture<Void> applyToTarget(Optional<? extends CollidableGameObject> target) {
        return target.map(this::applyToGameObject).orElse(CompletableFuture.completedFuture(null));
    }

    /**
     * Applies the effect to the self object if present.
     *
     * @param self the optional self object
     * @return a CompletableFuture representing the completion of the effect
     *         application
     */
    protected CompletableFuture<Void> applyToSelf(Optional<? extends CollidableGameObject> self) {
        return self.map(this::applyToGameObject).orElse(CompletableFuture.completedFuture(null));
    }

    /**
     * Applies the effect to a specific game object.
     *
     * @param gameObject the game object to apply the effect to
     * @return a CompletableFuture representing the completion of the effect
     *         application
     */
    protected abstract CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject);
}
