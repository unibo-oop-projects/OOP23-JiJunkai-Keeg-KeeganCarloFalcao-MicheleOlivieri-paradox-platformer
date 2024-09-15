package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Represents a game object with position and dimension.
 */
public interface GameObject {

    /**
     * Gets the current position of the game object.
     * 
     * @return the {@link Coord2D} representing the position of the game object.
     */
    Coord2D getPosition();

    /**
     * Gets the current dimension of the game object.
     * 
     * @return the {@link Dimension} representing the dimension of the game object.
     */
    Dimension getDimension();

    /**
     * Sets the position of the game object.
     * 
     * @param position the {@link Coord2D} to set as the new position. This
     *                 parameter should not be modified.
     */
    void setPosition(Coord2D position);

    /**
     * Sets the dimension of the game object.
     * 
     * @param dimension the {@link Dimension} to set as the new dimension. This
     *                  parameter should not be modified.
     */
    void setDimension(Dimension dimension);
}