package com.project.paradoxplatformer.model.effect;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Abstract base class for game effects, implementing the {@link Effect}
 * interface.
 * This class provides a default behavior for applying effects to both a target
 * and the object itself (self). It manages asynchronous execution using
 * {@link CompletableFuture}, allowing non-blocking operations for effect
 * application.
 * 
 * Subclasses are required to define how an effect is applied to individual game
 * objects
 * by implementing the {@link #applyToGameObject(CollidableGameObject)} method.
 */
public abstract class AbstractEffect implements Effect {

    /**
     * Applies the effect to both the target and the self object, if present.
     * This default implementation applies the effect to both objects concurrently.
     * 
     * @param target an {@link Optional} containing the target object, if available
     * @param self   an {@link Optional} containing the self object (the initiator
     *               of the effect), if available
     * @return a {@link CompletableFuture} representing the completion of applying
     *         the effect
     *         to both target and self.
     */
    @Override
    public CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target,
            Optional<? extends CollidableGameObject> self) {
        // Apply effect to both the target and self concurrently
        CompletableFuture<Void> targetFuture = applyToTarget(target);
        CompletableFuture<Void> selfFuture = applyToSelf(self);

        // Wait for both applications (target and self) to complete
        return CompletableFuture.allOf(targetFuture, selfFuture);
    }

    /**
     * Applies the effect to the target object if it is present.
     * 
     * This method wraps the effect application for the target in a
     * {@link CompletableFuture}, allowing asynchronous execution. If the
     * target is not present, a completed future is returned immediately.
     * 
     * @param target an {@link Optional} containing the target object
     * @return a {@link CompletableFuture} representing the completion of
     *         applying the effect to the target.
     */
    protected CompletableFuture<Void> applyToTarget(Optional<? extends CollidableGameObject> target) {
        // Apply effect to target if present, otherwise return a completed future
        return target.map(this::applyToGameObject).orElse(CompletableFuture.completedFuture(null));
    }

    /**
     * Applies the effect to the self object (the initiator of the effect) if it is
     * present.
     * 
     * Like the target, the self effect application is wrapped in a
     * {@link CompletableFuture} for asynchronous execution. If the self object is
     * not present, a completed future is returned immediately.
     * 
     * @param self an {@link Optional} containing the self object
     * @return a {@link CompletableFuture} representing the completion of
     *         applying the effect to the self object.
     */
    protected CompletableFuture<Void> applyToSelf(Optional<? extends CollidableGameObject> self) {
        // Apply effect to self if present, otherwise return a completed future
        return self.map(this::applyToGameObject).orElse(CompletableFuture.completedFuture(null));
    }

    /**
     * Abstract method to be implemented by subclasses to define how the effect
     * is applied to a given game object. This method should contain the logic
     * specific to the effect.
     * 
     * The effect is applied asynchronously and the result is encapsulated in a
     * {@link CompletableFuture} for non-blocking behavior.
     * 
     * @param gameObject the game object to which the effect is applied
     * @return a {@link CompletableFuture} representing the completion of the effect
     *         application to the specified game object.
     */
    protected abstract CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject);
}
