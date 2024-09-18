package com.project.paradoxplatformer.model.effect.impl;

import com.project.paradoxplatformer.model.effect.abstracts.AbstractOneTimeEffect;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Represents an effect that transports a game object to a specified
 * destination.
 */
public final class TransportEffect extends AbstractOneTimeEffect {

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
            //                System.out.println("TransportEffect: Applying to " + gameObject + " to " + destination);
            return target.map(this::applyToGameObject).orElseGet(() -> CompletableFuture.completedFuture(null));
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
            //                System.out.println("TransportEffect: Applying to " + gameObject);
            return self.map(this::applyToGameObject).orElseGet(() -> CompletableFuture.completedFuture(null));
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
    @Override
    protected CompletableFuture<Void> applyToGameObject(final CollidableGameObject gameObject) {
        return CompletableFuture.runAsync(() -> {
            if (gameObject instanceof PlayerModel) {
                ((PlayerModel) gameObject).setDisplacement(this.destination);
            } else {
                gameObject.setPosition(destination);
            }
        });
    }

}
