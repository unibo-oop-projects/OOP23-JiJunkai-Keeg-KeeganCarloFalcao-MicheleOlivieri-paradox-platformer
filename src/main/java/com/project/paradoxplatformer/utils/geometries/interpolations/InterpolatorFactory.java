package com.project.paradoxplatformer.utils.geometries.interpolations;

import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * A factory interface for creating different types of interpolators.
 * <p>
 * This interface provides methods to obtain various types of interpolators
 * for {@link Vector2D} objects, including linear and easing functions.
 * </p>
 */
public interface InterpolatorFactory {

    /**
     * Creates a linear interpolator.
     * <p>
     * This interpolator performs linear interpolation between two {@link Vector2D}
     * values.
     * </p>
     *
     * @return an {@link Interpolator} that performs linear interpolation
     */
    Interpolator<Vector2D> linear();

    /**
     * Creates an ease-in interpolator.
     * <p>
     * This interpolator performs easing-in interpolation, where the interpolation
     * starts slowly
     * and accelerates towards the end.
     * </p>
     *
     * @return an {@link Interpolator} that performs ease-in interpolation
     */
    Interpolator<Vector2D> easeIn();

    /**
     * Creates an ease-out interpolator.
     * <p>
     * This interpolator performs easing-out interpolation, where the interpolation
     * starts quickly
     * and decelerates towards the end.
     * </p>
     *
     * @return an {@link Interpolator} that performs ease-out interpolation
     */
    Interpolator<Vector2D> easeOut();

    /**
     * Creates an ease-in-out interpolator.
     * <p>
     * This interpolator performs ease-in-out interpolation, where the interpolation
     * starts slowly,
     * accelerates in the middle, and then decelerates towards the end.
     * </p>
     *
     * @return an {@link Interpolator} that performs ease-in-out interpolation
     */
    Interpolator<Vector2D> easeInOut();
}
