package com.project.paradoxplatformer.utils.collision;

import java.util.Collection;
import java.util.Set;

import com.project.paradoxplatformer.model.effect.EffectHandler;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

/**
 * Manages collisions between game objects and applies effects based on those
 * collisions. Delegates detection and observation to appropriate classes.
 */
public class CollisionManager {

    private final CollisionObserver collisionObserver;

    /**
     * Constructs a CollisionManager with the specified effect handler.
     *
     * @param effectHandler the effect handler to use for applying and resetting
     *                      effects
     */
    public CollisionManager(final EffectHandler effectHandler) {
        this.collisionObserver = new CollisionObserver(effectHandler);
    }

    /**
     * Handles collisions between the player and other collidable game objects.
     *
     * @param collidableGameObjects a collection of collidable game objects to check
     *                              for collisions
     * @param player                the player game object to check for collisions
     *                              with
     */
    public void handleCollisions(final Collection<? extends CollidableGameObject> collidableGameObjects,
            final CollidableGameObject player) {
        // Detect collisions between the player and other collidable objects
        final Set<CollidableGameObject> collidingObjects = CollisionDetector.detect(collidableGameObjects, player);

        // Handle collision observation and trigger effects
        collisionObserver.observeCollisions(collidingObjects, player);
    }
}
