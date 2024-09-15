package com.project.paradoxplatformer.utils.geometries.vector.api;

import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * A sealed interface representing a 2D vector with various operations.
 * 
 * <p>
 * This interface defines operations for vector manipulation, including
 * magnitude,
 * direction, and vector arithmetic.
 * </p>
 */
public sealed interface Vector2D permits AbstractVector {

    /**
     * Calculates the magnitude of the vector.
     * 
     * @return the magnitude of the vector
     */
    double magnitude();

    /**
     * Calculates the direction of the vector in radians.
     * 
     * @return the direction of the vector
     */
    double direction();

    /**
     * Retrieves the y-component of the vector.
     * 
     * @return the y-component of the vector
     */
    double yComponent();

    /**
     * Retrieves the x-component of the vector.
     * 
     * @return the x-component of the vector
     */
    double xComponent();

    /**
     * Adds another vector to this vector.
     * 
     * @param vector the vector to add
     * @return the resulting vector after addition
     */
    Vector2D add(Vector2D vector);

    /**
     * Scales the vector by a given scalar.
     * 
     * @param scalar the scalar value to multiply the vector by
     * @return the resulting scaled vector
     */
    Vector2D scalar(double scalar);

    /**
     * Subtracts another vector from this vector.
     * 
     * @param e the vector to subtract
     * @return the resulting vector after subtraction
     */
    Vector2D sub(Vector2D e);

    /**
     * Converts the vector to a 2D coordinate.
     * 
     * @return the 2D coordinate representation of the vector
     */
    Coord2D convert();
}