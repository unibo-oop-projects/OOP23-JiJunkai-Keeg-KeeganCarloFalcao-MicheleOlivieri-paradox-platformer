package com.project.paradoxplatformer.utils.geometries.coordinates;

/**
 * Represents a point in a 2D coordinate system.
 * <p>
 * This record holds the x and y coordinates of a point and provides
 * static methods for creating commonly used coordinate values.
 * </p>
 * 
 * @param x The X coordinate.
 * 
 * @param y The Y coordinate.
 */
public final record Coord2D(double x, double y) {

    /**
     * Returns the origin point (0, 0) in a 2D coordinate system.
     * <p>
     * This is a convenience method to get a coordinate representing
     * the origin of the coordinate system.
     * </p>
     *
     * @return a {@link Coord2D} instance with x and y set to 0
     */
    public static Coord2D origin() {
        return new Coord2D(0, 0);
    }

    /**
     * Returns a coordinate with a randomly generated x value and a fixed y value.
     * <p>
     * This method generates a random integer for the x coordinate between 0 and
     * 500, while the y coordinate is set to the specified fixed value.
     * </p>
     *
     * @param fixedY the fixed y coordinate value
     * @return a {@link Coord2D} instance with a random x value and the specified y
     *         value
     */
    public static Coord2D randomX(final double fixedY) {
        final int maxCoordinateValue = 500;
        return new Coord2D(randomInt(0, maxCoordinateValue), fixedY);
    }

    /**
     * Returns a coordinate with a randomly generated y value and a fixed x value.
     * <p>
     * This method generates a random integer for the y coordinate between 0 and
     * 500, while the x coordinate is set to the specified fixed value.
     * </p>
     *
     * @param fixedX the fixed x coordinate value
     * @return a {@link Coord2D} instance with the specified x value and a random y
     *         value
     */
    public static Coord2D randomY(final double fixedX) {
        final int maxCoordinateValue = 500;
        return new Coord2D(fixedX, randomInt(0, maxCoordinateValue));
    }

    /**
     * Generates a random integer between the specified minimum and maximum values
     * (inclusive).
     * <p>
     * This is a helper method used internally to generate random coordinates.
     * </p>
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (inclusive)
     * @return a random integer between min and max
     */
    private static int randomInt(final int min, final int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
