package com.project.paradoxplatformer.utils.effect;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Represents an effect that transports a game object to a specified
 * destination.
 */
public class TransportEffect extends AbstractEffect {
    private final Coord2D destination;
    private final boolean applyToTarget;

    /**
     * Creates a new TransportEffect.
     *
     * @param destination   the destination coordinates
     * @param applyToTarget whether to apply the effect to the self object
     */
    public TransportEffect(Coord2D destination, boolean applyToTarget) {
        this.destination = destination;
        this.applyToTarget = applyToTarget;
    }

    @Override
    protected CompletableFuture<Void> applyToTarget(Optional<? extends CollidableGameObject> target) {
        if (applyToTarget) {
            return target.map(gameObject -> {
                System.out.println("TransportEffect: Applying to " + target.get() + "to " + destination);
                return applyToGameObject(gameObject);
            }).orElseGet(() -> CompletableFuture.completedFuture(null));
        }
        return CompletableFuture.completedFuture(null);
    }

    @Override
    protected CompletableFuture<Void> applyToSelf(Optional<? extends CollidableGameObject> self) {
        return self.map(gameObject -> {
            System.out.println("TransportEffect: Applying to " + self.get());
            return applyToGameObject(gameObject);
        }).orElseGet(() -> CompletableFuture.completedFuture(null));
    }

    /**
     * Applies the transport effect to a game object.
     *
     * @param gameObject the game object to apply the effect to
     * @return a CompletableFuture that completes when the effect is applied
     */
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        gameObject.setPosition(new Coord2D(0, 0));
        gameObject.setPosition(destination);
        return CompletableFuture.completedFuture(null);
    }
}
