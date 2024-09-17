package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * An abstract base class representing a mutable game object.
 * <p>
 * This class provides default implementations for managing an object's key and
 * basic methods related to position, dimension, collision type, and speed.
 * Subclasses must provide concrete implementations for the abstract methods
 * related to the object's state and attributes.
 * </p>
 */
public abstract class AbstractMutableObject implements MutableObject {

    private final int id;

    /**
     * Constructs a basic mutable object with a given id.
     * @param key unique id
     */
    public AbstractMutableObject(final int key) {
        this.id = key;
    }

    /**
     * Returns the position of this mutable object.
     * 
     * @return the position as a {@link Coord2D} object
     */
    @Override
    public abstract Coord2D getPosition();

    /**
     * Returns the dimension of this mutable object.
     * 
     * @return the dimension as a {@link Dimension} object
     */
    @Override
    public abstract Dimension getDimension();

    /**
     * Sets the position of this mutable object.
     * 
     * @param position the new position to set, as a {@link Coord2D} object
     */
    @Override
    public abstract void setPosition(Coord2D position);

    /**
     * Returns the collision type of this mutable object.
     * 
     * @return the collision type as a {@link CollisionType} object
     */
    @Override
    public abstract CollisionType getCollisionType();

    /**
     * Updates the state of this mutable object.
     * 
     * @param dt the time delta to use for updating the state
     */
    @Override
    public abstract void updateState(long dt);

    /**
     * Sets the dimension of this mutable object.
     * 
     * @param dimension the new dimension to set, as a {@link Dimension} object
     */
    @Override
    public abstract void setDimension(Dimension dimension);

    /**
     * Returns the speed of this mutable object.
     * 
     * @return the speed as a {@link Vector2D} object
     */
    @Override
    public abstract Vector2D getSpeed();

    /**
     * Returns the ID of this mutable object.
     * 
     * @return the ID as an integer
     */
    @Override
    public int getID() {
        return this.id;
    }
}
