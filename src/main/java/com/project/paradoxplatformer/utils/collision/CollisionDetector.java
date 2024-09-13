package com.project.paradoxplatformer.utils.collision;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import java.util.List;

/**
 * A utility class for detecting collisions between game objects.
 */
public class CollisionDetector {

    /**
     * Checks if two collidable game objects are colliding.
     *
     * @param obj1 the first collidable game object
     * @param obj2 the second collidable game object
     * @param <T>  the type of the collidable game objects
     * @return true if the objects are colliding, false otherwise
     */
    public static <T extends CollidableGameObject> boolean isColliding(T obj1, T obj2) {
        // Check if the objects are not colliding based on their positions and
        // dimensions
        return !(obj1.getPosition().x() + obj1.getDimension().width() <= obj2.getPosition().x() ||
                obj1.getPosition().x() >= obj2.getPosition().x() + obj2.getDimension().width() ||
                obj1.getPosition().y() + obj1.getDimension().height() <= obj2.getPosition().y() ||
                obj1.getPosition().y() >= obj2.getPosition().y() + obj2.getDimension().height());
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
    public static <T extends CollidableGameObject> boolean hasCollision(T obj, List<T> collidableGameObjects) {
        // Check if the object is colliding with any other object in the list
        return collidableGameObjects.stream()
                .anyMatch(other -> !other.equals(obj) && isColliding(obj, other));
    }

}
