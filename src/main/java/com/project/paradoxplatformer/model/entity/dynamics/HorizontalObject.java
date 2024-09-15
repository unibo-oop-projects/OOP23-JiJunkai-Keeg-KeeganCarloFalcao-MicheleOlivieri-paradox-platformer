package com.project.paradoxplatformer.model.entity.dynamics;

import com.project.paradoxplatformer.utils.geometries.modifiers.Direction;

/**
 * Represents an object that can move horizontally.
 */
public interface HorizontalObject {

    /**
     * Moves the object to the left.
     */
    void moveLeft();

    /**
     * Moves the object to the right.
     */
    void moveRight();

    /**
     * Stops the object's horizontal movement.
     */
    void stop();

    /**
     * Gets the base delta value used for movement calculations.
     * 
     * @return the base delta value.
     */
    double getBaseDelta();

    /**
     * Gets the current direction of the object.
     * 
     * @return the {@link Direction} representing the current direction of the
     *         object.
     */
    Direction direction();
}
