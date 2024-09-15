package com.project.paradoxplatformer.utils.geometries.vector.api;

import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.coordinates.api.Cartesian;

/**
 * Represents an abstract 2D vector with Cartesian coordinates.
 * <p>
 * This class provides common functionality for 2D vectors, including
 * magnitude, direction, and conversion to Cartesian coordinates. It also
 * defines abstract methods for vector operations that must be implemented
 * by subclasses.
 * </p>
 */
public sealed abstract class AbstractVector implements Vector2D permits Simple2DVector {

    protected Cartesian cartesian;
    private double magnitude;
    private double angle;

    /**
     * Constructs an {@link AbstractVector} with the specified x and y components.
     * <p>
     * This constructor initializes the vector using Cartesian coordinates and
     * calculates the magnitude and angle from these coordinates.
     * </p>
     *
     * @param x the x component of the vector
     * @param y the y component of the vector
     */
    protected AbstractVector(double x, double y) {
        this.cartesian = new Cartesian(x, y);
        this.magnitude = cartesian.toPolar().getMagnitude();
        this.angle = cartesian.toPolar().getAngle();
    }

    /**
     * Returns the magnitude of this vector.
     * <p>
     * The magnitude is the length of the vector in Cartesian space.
     * </p>
     *
     * @return the magnitude of this vector
     */
    @Override
    public double magnitude() {
        return this.magnitude;
    }

    /**
     * Returns the direction (angle) of this vector.
     * <p>
     * The direction is the angle of the vector in polar coordinates.
     * </p>
     *
     * @return the angle (direction) of this vector
     */
    @Override
    public double direction() {
        return this.angle;
    }

    /**
     * Converts this vector to a {@link Coord2D} representation.
     * <p>
     * This method returns the vector's Cartesian coordinates as a {@link Coord2D}
     * instance.
     * </p>
     *
     * @return a {@link Coord2D} instance representing this vector's Cartesian
     *         coordinates
     */
    @Override
    public Coord2D convert() {
        return new Coord2D(this.xComponent(), this.yComponent());
    }

    /**
     * Adds the specified vector to this vector and returns the result.
     * <p>
     * This method performs vector addition and must be implemented by subclasses.
     * </p>
     *
     * @param vector the {@link Vector2D} to add to this vector
     * @return a new {@link Vector2D} that is the result of the addition
     */
    public abstract Vector2D add(Vector2D vector);

    /**
     * Multiplies this vector by the specified scalar and returns the result.
     * <p>
     * This method scales the vector by the given scalar and must be implemented by
     * subclasses.
     * </p>
     *
     * @param scalar the scalar value to multiply this vector by
     * @return a new {@link Vector2D} that is the result of the scaling
     */
    public abstract Vector2D scalar(double scalar);

    /**
     * Returns the y component of this vector.
     * <p>
     * This method retrieves the y component of the vector's Cartesian coordinates
     * and must be implemented by subclasses.
     * </p>
     *
     * @return the y component of this vector
     */
    public abstract double yComponent();

    /**
     * Returns the x component of this vector.
     * <p>
     * This method retrieves the x component of the vector's Cartesian coordinates
     * and must be implemented by subclasses.
     * </p>
     *
     * @return the x component of this vector
     */
    public abstract double xComponent();

    /**
     * Returns a string representation of this vector.
     * <p>
     * The string representation includes the Cartesian coordinates and magnitude.
     * </p>
     *
     * @return a string representation of this vector
     */
    @Override
    public String toString() {
        return "{" + cartesian + ", Mag: " + magnitude + "}";
    }
}
