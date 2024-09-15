package com.project.paradoxplatformer.utils.geometries.interpolations;

import java.util.Optional;
import java.util.function.UnaryOperator;

import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * Implementation of the {@link InterpolatorFactory} interface for creating
 * different types of interpolators.
 * <p>
 * This class provides implementations for linear interpolation and various
 * easing functions
 * for {@link Vector2D} objects.
 * </p>
 */
public final class InterpolatorFactoryImpl implements InterpolatorFactory {

    private static final double LINEAR_EXPO = 1.d;
    private static final double EASY_IN_EXPO = 3.d;
    private static final double EASY_OUT_EXPO = 2.d;
    private static final double UNIT = 1.d;
    private static final double NULL_ALGEBRIC_VALUE = 0.d;

    /**
     * Creates an interpolator using a base function and exponent for easing
     * calculations.
     * <p>
     * This method returns an {@link Interpolator} that performs interpolation based
     * on the provided base function
     * and exponent, optionally adjusting for any residual values.
     * </p>
     *
     * @param base     a {@link UnaryOperator} function to apply for interpolation
     * @param exponent the exponent to use in the interpolation calculation
     * @param residuo  an optional residual value for further adjustment
     * @return an {@link Interpolator} for {@link Vector2D} that performs
     *         interpolation
     */
    private Interpolator<Vector2D> templateEase(final UnaryOperator<Double> base, final double exponent,
            final Optional<Double> residuo) {
        return (s, e, t) -> new Simple2DVector(
                s.xComponent() + (e.xComponent() - s.xComponent()) *
                        (residuo.orElse(NULL_ALGEBRIC_VALUE) * residuo.map(sign -> -UNIT).orElse(UNIT)
                                + Math.min(Math.pow(base.apply(t), exponent), UNIT)),
                s.yComponent() + (e.yComponent() - s.yComponent()) *
                        (residuo.orElse(NULL_ALGEBRIC_VALUE) * residuo.map(sign -> -UNIT).orElse(UNIT)
                                + Math.min(Math.pow(base.apply(t), exponent), UNIT)));
    }

    @Override
    public Interpolator<Vector2D> linear() {
        /**
         * Creates a linear interpolator.
         * <p>
         * This interpolator performs linear interpolation between two {@link Vector2D}
         * values.
         * </p>
         *
         * @return an {@link Interpolator} for {@link Vector2D} that performs linear
         *         interpolation
         */
        return templateEase(UnaryOperator.identity(), LINEAR_EXPO, Optional.empty());
    }

    @Override
    public Interpolator<Vector2D> easeIn() {
        /**
         * Creates an ease-in interpolator.
         * <p>
         * This interpolator performs easing-in interpolation, where the interpolation
         * starts slowly
         * and accelerates towards the end.
         * </p>
         *
         * @return an {@link Interpolator} for {@link Vector2D} that performs ease-in
         *         interpolation
         */
        return templateEase(UnaryOperator.identity(), EASY_IN_EXPO, Optional.empty());
    }

    @Override
    public Interpolator<Vector2D> easeOut() {
        /**
         * Creates an ease-out interpolator.
         * <p>
         * This interpolator performs easing-out interpolation, where the interpolation
         * starts quickly
         * and decelerates towards the end.
         * </p>
         *
         * @return an {@link Interpolator} for {@link Vector2D} that performs ease-out
         *         interpolation
         */
        return templateEase(t -> 1 - t, EASY_OUT_EXPO, Optional.of(UNIT));
    }

    @Override
    public Interpolator<Vector2D> easeInOut() {
        /**
         * Creates an ease-in-out interpolator.
         * <p>
         * This interpolator performs ease-in-out interpolation, where the interpolation
         * starts slowly,
         * accelerates in the middle, and then decelerates towards the end.
         * </p>
         *
         * @return an {@link Interpolator} for {@link Vector2D} that performs
         *         ease-in-out interpolation
         */
        return templateEase(t -> 1 - t, 2.d, Optional.of(UNIT));
    }
}
