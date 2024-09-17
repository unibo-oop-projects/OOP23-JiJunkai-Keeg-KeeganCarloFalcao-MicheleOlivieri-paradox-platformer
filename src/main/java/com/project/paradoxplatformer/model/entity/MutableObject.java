package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * Represents a mutable game object that can be updated and has additional
 * properties.
 */
public interface MutableObject extends CollidableGameObject {

    /**
     * Updates the state of the object based on the time delta.
     * 
     * @param dt the time delta since the last update. This parameter should not be
     *           modified.
     */
    void updateState(long dt);

    /**
     * Gets the unique identifier of the object.
     * 
     * @return the unique identifier of the object.
     */
    int getID();

    /**
     * Gets the current speed of the object.
     * 
     * @return the {@link Vector2D} representing the speed of the object.
     */
    Vector2D getSpeed();

    /**
     * Gets the base delta value used for state updates.
     * 
     * @return the base delta value.
     */
    double getBaseDelta();
}
