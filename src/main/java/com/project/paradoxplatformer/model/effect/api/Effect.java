package com.project.paradoxplatformer.model.effect.api;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

/**
 * Represents an effect that can be applied to game objects. Effects may modify
 * game objects in various ways, such as changing their properties or triggering
 * specific behaviors. Effects can also be one-time or re-creatable.
 */
public interface Effect {

    /**
     * Applies this effect to the specified target and self game objects.
     * This method may modify the state of the provided game objects asynchronously.
     * 
     * @param target An Optional containing the target game object this effect will
     *               be applied to.
     * @param self   An Optional containing the game object that is applying the
     *               effect.
     * @return A CompletableFuture that represents the asynchronous completion of
     *         the effect application.
     */
    CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target,
            Optional<? extends CollidableGameObject> self);

    /**
     * Provides a completed future with no action. This is a utility method to use
     * when no effect needs to be applied, avoiding the need to create new futures.
     * 
     * @return A CompletableFuture representing the completion of no action.
     */
    static CompletableFuture<Void> empty() {
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Determines if this effect is a one-time effect.
     * A one-time effect is applied once and then removed or discarded.
     * 
     * @return true if this effect is meant to be applied only once and then
     *         removed; false otherwise.
     */
    boolean isOneTimeEffect();

    /**
     * Recreates a new instance of this effect. The default implementation returns
     * the current instance, which is suitable for effects that do not need to be
     * recreated differently.
     * 
     * Subclasses can override this method to provide specific recreation logic.
     * 
     * @return A new instance of this effect, or the current instance if no specific
     *         recreation logic is needed.
     */
    default Effect recreate() {
        return this;
    }
}
