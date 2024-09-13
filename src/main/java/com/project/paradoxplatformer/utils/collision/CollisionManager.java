package com.project.paradoxplatformer.utils.collision;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

import com.project.paradoxplatformer.model.effect.EffectHandler;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

/**
 * Manages collisions between game objects and applies effects based on those
 * collisions.
 */
public class CollisionManager {

    private EffectHandler effectHandler;

    /**
     * Constructs a CollisionManager with the specified effect handler.
     *
     * @param effectHandler the effect handler to use for applying and resetting
     *                      effects
     */
    public CollisionManager(EffectHandler effectHandler) {
        this.effectHandler = effectHandler;
    }

    /**
     * Handles collisions between the player and other collidable game objects.
     * Detects collisions and observes the collision results by applying and
     * resetting effects.
     *
     * @param collidableGameObjects a collection of collidable game objects to check
     *                              for collisions
     * @param player                the player game object to check for collisions
     *                              with
     */
    public void handleCollisions(Collection<? extends CollidableGameObject> collidableGameObjects,
            CollidableGameObject player) {
        // Detect collisions between the player and other collidable objects
        Set<CollidableGameObject> collidingObjects = detectCollisions(collidableGameObjects, player);

        // Observe and handle collisions by applying effects and resetting
        observeCollisions(collidingObjects, player, effectHandler::applyEffects, effectHandler::reset);
    }

    /**
     * Detects collisions between the player and other collidable objects.
     *
     * @param collidableGameObjects a collection of collidable game objects to check
     *                              for collisions
     * @param player                the player game object to check for collisions
     *                              with
     * @return a set of colliding game objects
     */
    private Set<CollidableGameObject> detectCollisions(Collection<? extends CollidableGameObject> collidableGameObjects,
            CollidableGameObject player) {
        Set<CollidableGameObject> collidingObjects = new HashSet<>();

        // Check if each object in the collection is colliding with the player
        collidableGameObjects.stream()
                .filter(object -> object != player || collidableGameObjects.contains(object))
                .forEach(object -> {
                    if (CollisionDetector.isColliding(player, object)) {
                        collidingObjects.add(object);
                    }
                });

        return collidingObjects;
    }

    /**
     * Observes collisions by applying start and end actions for each colliding
     * object.
     *
     * @param collidingObjects the set of colliding game objects
     * @param player           the player game object involved in collisions
     * @param onCollideStart   action to perform when a collision starts
     * @param onCollideEnd     action to perform when a collision ends
     */
    private void observeCollisions(Set<CollidableGameObject> collidingObjects,
            CollidableGameObject player,
            BiConsumer<CollidableGameObject, CollidableGameObject> onCollideStart,
            BiConsumer<CollidableGameObject, CollisionType> onCollideEnd) {

        // Apply start collision action for each colliding object
        collidingObjects.forEach(object -> onCollideStart.accept(player, object));

        // Apply end collision action for each colliding object
        collidingObjects.forEach(object -> {
            onCollideEnd.accept(object, object.getCollisionType());
        });
    }

    /**
     * Sets a new effect handler for this CollisionManager.
     *
     * @param effectHandler the new effect handler to use
     */
    public void setEffectHandler(EffectHandler effectHandler) {
        this.effectHandler = effectHandler;
    }
}
