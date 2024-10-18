package com.project.paradoxplatformer.utils.collision;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

/**
 * A utility class for detecting collisions between game objects. This class
 * provides static methods to check for collisions between game objects and
 * within lists of collidable objects.
 */
/**
 * Detects collisions between the player and other collidable objects.
 */
public final class CollisionDetector {

    // Private constructor to prevent instantiation
    private CollisionDetector() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
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
    public static Set<CollidableGameObject> detect(
            final Collection<? extends CollidableGameObject> collidableGameObjects,
            final CollidableGameObject player) {
        final Set<CollidableGameObject> collidingObjects = new HashSet<>();

        collidableGameObjects.stream()
                .filter(object -> !object.equals(player))
                .forEach(object -> {
                    if (isColliding(player, object)) {
                        collidingObjects.add(object);
                    }
                });

        return collidingObjects;
    }

    /**
     * Checks if two collidable game objects are colliding.
     *
     * @param obj1 the first collidable game object
     * @param obj2 the second collidable game object
     * @param <T>  the type of the collidable game objects
     * @return true if the objects are colliding, false otherwise
     */
    public static <T extends CollidableGameObject> boolean isColliding(final T obj1, final T obj2) {
        // Check if the objects are not colliding based on their positions and
        // dimensions
        return !(obj1.getPosition().x() + obj1.getDimension().width() <= obj2.getPosition().x()
                || obj1.getPosition().x() >= obj2.getPosition().x() + obj2.getDimension().width()
                || obj1.getPosition().y() + obj1.getDimension().height() <= obj2.getPosition().y()
                || obj1.getPosition().y() >= obj2.getPosition().y() + obj2.getDimension().height());
    }

    /**
     * Checks if a given collidable game object is colliding with any object in a
     * list of collidable objects.
     *
     * @param obj                   the collidable game object to check for
     *                              collisions
     * @param collidableGameObjects the list of collidable game objects to check
     *                              against
     * @param <T>                   the type of the collidable game objects
     * @return true if the object is colliding with any object in the list, false
     *         otherwise
     */
    public static <T extends CollidableGameObject> boolean hasCollision(final T obj,
            final List<T> collidableGameObjects) {
        // Check if the object is colliding with any other object in the list
        return collidableGameObjects.stream()
                .anyMatch(other -> !other.equals(obj) && isColliding(obj, other));
    }
}
