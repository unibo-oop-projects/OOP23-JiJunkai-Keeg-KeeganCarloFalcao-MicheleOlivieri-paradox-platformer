package com.project.paradoxplatformer.utils.geometries.vector.api;

import com.project.paradoxplatformer.utils.geometries.coordinates.api.Polar;

/**
 * Represents a 2D vector in polar coordinates.
 * <p>
 * This class extends Simple2DVector and provides functionality to initialize
 * vectors from polar coordinates.
 * </p>
 */
public final class Polar2DVector extends Simple2DVector {

    /**
     * Constructs a new Polar2DVector using the given polar coordinates.
     * <p>
     * The polar coordinates are converted to Cartesian coordinates for
     * initialization.
     * </p>
     *
     * @param r     the radial distance from the origin
     * @param theta the angle in radians
     */
    public Polar2DVector(final double r, final double theta) {
        super(new Polar(r, theta).toCartesian().getX(), new Polar(r, theta).toCartesian().getY());
    }

    /**
     * Creates a null vector with zero magnitude and direction.
     *
     * @return a new Polar2DVector representing the null vector
     */
    public static Vector2D nullVector() {
        return new Polar2DVector(0.d, 0.d);
    }
}
