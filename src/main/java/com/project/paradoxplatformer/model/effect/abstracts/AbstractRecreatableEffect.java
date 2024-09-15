package com.project.paradoxplatformer.model.effect.abstracts;

import com.project.paradoxplatformer.model.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Abstract base class for effects that can be applied and then recreated.
 * These effects can be reapplied multiple times, unlike one-time effects.
 */
public abstract class AbstractRecreatableEffect extends AbstractEffect implements RecreateableEffect {

    /**
     * Applies the effect to both target and self, and then logs that the effect
     * can be recreated.
     *
     * @param target the optional target object
     * @param self   the optional self object
     * @return a CompletableFuture representing the completion of the effect
     */
    @Override
    public CompletableFuture<Void> apply(final Optional<? extends CollidableGameObject> target,
            final Optional<? extends CollidableGameObject> self) {
        // Apply the effect and log that it can be recreated
        return super.apply(target, self).thenRun(() -> {
            // System.out.println("Re-creatable effect has been applied and could be
            // recreated.");
        });
    }

    /**
     * Indicates that this is not a one-time effect, as it can be reapplied.
     * 
     * @return false because recreatable effects are not one-time effects
     */
    @Override
    public boolean isOneTimeEffect() {
        return false;
    }

    /**
     * Abstract method to recreate the effect. Subclasses must provide their
     * own implementation for how the effect is recreated.
     * 
     * @return a new instance of the recreatable effect
     */
    @Override
    public abstract RecreateableEffect recreate();
}
