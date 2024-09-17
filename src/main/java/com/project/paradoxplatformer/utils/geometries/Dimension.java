package com.project.paradoxplatformer.utils.geometries;

/**
 * Represents the dimensions of a two-dimensional object.
 * <p>
 * This record stores the width and height of an object and provides
 * basic methods to interact with these dimensions.
 * </p>
 *
 * @param width  the width of the dimension
 * @param height the height of the dimension
 */
public record Dimension(double width, double height) {

    /**
     * Returns a {@link Dimension} representing a zero-sized object.
     * <p>
     * This is a convenience method that creates a {@link Dimension} with
     * both width and height set to zero.
     * </p>
     *
     * @return a {@link Dimension} instance with width and height of 0
     */
    public static Dimension dot() {
        return new Dimension(0, 0);
    }
}
