package com.project.paradoxplatformer.utils.geometries.vector.api;

/**
 * Represents a 2D vector with Cartesian coordinates.
 * <p>
 * This class provides basic operations for 2D vectors, including addition,
 * subtraction,
 * and scalar multiplication. It extends {@link AbstractVector} and can be used
 * in various geometric computations.
 * </p>
 */
public sealed class Simple2DVector extends AbstractVector permits Polar2DVector {

    /**
     * Constructs a {@link Simple2DVector} with the specified x and y components.
     * <p>
     * This constructor initializes the vector with the given Cartesian coordinates.
     * </p>
     *
     * @param x the x component of the vector
     * @param y the y component of the vector
     */
    public Simple2DVector(final double x, final double y) {
        super(x, y);
    }

    /**
     * Adds the specified vector to this vector and returns the result.
     * <p>
     * This method performs vector addition by summing the corresponding components
     * of this vector and the specified vector.
     * </p>
     *
     * @param vector the {@link Vector2D} to add to this vector
     * @return a new {@link Vector2D} that is the result of the addition
     */
    @Override
    public Vector2D add(final Vector2D vector) {
        return new Simple2DVector(
                vector.xComponent() + this.xComponent(),
                vector.yComponent() + this.yComponent());
    }

    /**
     * Multiplies this vector by the specified scalar and returns the result as a
     * {@link Polar2DVector}.
     * <p>
     * This method scales the vector by the given scalar and converts the result to
     * polar coordinates.
     * </p>
     *
     * @param scalar the scalar value to multiply this vector by
     * @return a new {@link Polar2DVector} that represents the scaled vector in
     *         polar coordinates
     */
    @Override
    public Vector2D scalar(final double scalar) {
        return new Polar2DVector(scalar * this.magnitude(), this.direction());
    }

    /**
     * Returns the y component of this vector.
     * <p>
     * This method retrieves the y component of the vector's Cartesian coordinates.
     * </p>
     *
     * @return the y component of this vector
     */
    @Override
    public double yComponent() {
        return getCartesian().getY();
    }

    /**
     * Returns the x component of this vector.
     * <p>
     * This method retrieves the x component of the vector's Cartesian coordinates.
     * </p>
     *
     * @return the x component of this vector
     */
    @Override
    public double xComponent() {
        return getCartesian().getX();
    }

    /**
     * Subtracts the specified vector from this vector and returns the result.
     * <p>
     * This method performs vector subtraction by subtracting the corresponding
     * components
     * of the specified vector from this vector.
     * </p>
     *
     * @param vector the {@link Vector2D} to subtract from this vector
     * @return a new {@link Vector2D} that is the result of the subtraction
     */
    @Override
    public Vector2D sub(final Vector2D vector) {
        return new Simple2DVector(
                -vector.xComponent() + this.xComponent(),
                -vector.yComponent() + this.yComponent());
    }
}
