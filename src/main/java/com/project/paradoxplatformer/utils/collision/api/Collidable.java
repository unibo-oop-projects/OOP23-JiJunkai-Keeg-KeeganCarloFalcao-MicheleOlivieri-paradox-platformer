package com.project.paradoxplatformer.utils.collision.api;

/**
 * Defines the contract for objects that can participate in collision detection.
 * Objects implementing this interface must provide information about their
 * collision type.
 */
public interface Collidable {

    /**
     * Returns the collision type of the object.
     * This information can be used to determine specific collision handling logic.
     *
     * @return the collision type of the object
     */
    CollisionType getCollisionType();

}