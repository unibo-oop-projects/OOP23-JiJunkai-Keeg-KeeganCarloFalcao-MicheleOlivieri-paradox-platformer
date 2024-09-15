package com.project.paradoxplatformer.utils.geometries.interpolations;

/**
 * An interface for performing interpolation between two values.
 * <p>
 * This interface provides a method for linear interpolation between a start
 * and end value based on a parameter {@code t}.
 * </p>
 *
 * @param <V> the type of the values to interpolate
 */
public interface Interpolator<V> {

     /**
      * Linearly interpolates between two values.
      * <p>
      * This method computes an interpolated value between {@code start} and
      * {@code end} based on the interpolation factor {@code t}. The value of
      * {@code t} should be between 0 and 1, where 0 returns {@code start},
      * 1 returns {@code end}, and values in between return a proportionate value.
      * </p>
      *
      * @param start the starting value of the interpolation
      * @param end   the ending value of the interpolation
      * @param t     the interpolation factor (should be between 0 and 1)
      * @return the interpolated value
      */
     V lerp(V start, V end, double t);
}
