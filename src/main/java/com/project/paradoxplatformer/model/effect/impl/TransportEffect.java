package com.project.paradoxplatformer.model.effect.impl;

import com.project.paradoxplatformer.model.effect.AbstractRecreatableEffect;
import com.project.paradoxplatformer.model.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Represents an effect that transports a game object to a specified
 * destination.
 */
public final class TransportEffect extends AbstractRecreatableEffect {

    private final Coord2D destination; // The destination coordinates for transport
    private final boolean applyToTarget; // Whether to apply the effect to the target

    /**
     * Creates a new TransportEffect.
     *
     * @param destination   the destination coordinates
     * @param applyToTarget whether to apply the effect to the target object
     */
    public TransportEffect(final Coord2D destination, final boolean applyToTarget) {
        this.destination = destination;
        this.applyToTarget = applyToTarget;
    }

    /**
     * Applies the transport effect to the target game object.
     *
     * @param target the target game object to apply the effect to
     * @return a CompletableFuture that completes when the effect is applied
     */
    @Override
    protected CompletableFuture<Void> applyToTarget(final Optional<? extends CollidableGameObject> target) {
        if (applyToTarget) {
            return target.map(gameObject -> {
                System.out.println("TransportEffect: Applying to " + gameObject + " to " + destination);
                return applyToGameObject(gameObject);
            }).orElseGet(() -> CompletableFuture.completedFuture(null));
        }
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Applies the transport effect to the self game object.
     *
     * @param self the self game object to apply the effect to
     * @return a CompletableFuture that completes when the effect is applied
     */
    @Override
    protected CompletableFuture<Void> applyToSelf(final Optional<? extends CollidableGameObject> self) {
        if (!applyToTarget) {
            return self.map(gameObject -> {
                System.out.println("TransportEffect: Applying to " + gameObject);
                return applyToGameObject(gameObject);
            }).orElseGet(() -> CompletableFuture.completedFuture(null));
        }
        return CompletableFuture.completedFuture(null);
    }

    /**
     * Applies the transport effect to a game object by setting its position to
     * the destination coordinates.
     *
     * @param gameObject the game object to apply the effect to
     * @return a CompletableFuture that completes when the effect is applied
     */
    protected CompletableFuture<Void> applyToGameObject(final CollidableGameObject gameObject) {
        return CompletableFuture.runAsync(() -> gameObject.setPosition(destination));
    }

    /**
     * Creates a new instance of this TransportEffect, effectively recreating it.
     *
     * @return a new TransportEffect instance with a random destination and the same
     *         applyToTarget value
     */
    @Override
    public RecreateableEffect recreate() {
        System.out.println("Transport Effect gets recreated");
        return new TransportEffect(Coord2D.randomX(100), applyToTarget);
    }
}
