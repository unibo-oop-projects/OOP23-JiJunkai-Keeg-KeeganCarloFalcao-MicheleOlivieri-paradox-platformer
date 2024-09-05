package com.project.paradoxplatformer.utils.effect.api;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import com.project.paradoxplatformer.model.entity.CollidableGameObject;

/**
 * Represents an effect that can be applied to game objects.
 */
public interface Effect {

    /**
     * Applies this effect to the specified target and self game objects.
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

    static CompletableFuture<Void> empty() {
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Determines if this effect is a one-time effect.
     * 
     * @return true if this effect is meant to be applied only once and then
     *         removed; false otherwise.
     */
    default boolean isOneTimeEffect() {
        return false;
    }

    /**
     * Recreates a new instance of this effect. The default implementation returns
     * the current instance,
     * which is suitable for effects that do not need to be recreated differently.
     * 
     * @return A new instance of this effect, or the current instance if no specific
     *         recreation logic is needed.
     */
    default Effect recreate() {
        return this;
    }
}
