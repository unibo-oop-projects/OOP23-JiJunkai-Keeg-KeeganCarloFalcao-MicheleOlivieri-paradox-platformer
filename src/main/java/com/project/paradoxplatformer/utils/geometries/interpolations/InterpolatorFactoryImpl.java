package com.project.paradoxplatformer.utils.geometries.interpolations;

import java.util.Optional;
import java.util.function.UnaryOperator;

import com.project.paradoxplatformer.utils.geometries.vector.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public final class InterpolatorFactoryImpl implements InterpolatorFactory{


    private static final double LINEAR_EXPO = 1.d;
    private static final double EASY_IN_EXPO = 3.d;
    private static final double EASY_OUT_EXPO = 2.d;
    private static final double UNIT = 1.d;
    private static final double NULL_ALGEBRIC_VALUE = 0.d;

    private Interpolator<Vector2D> templateEase(UnaryOperator<Double> base, double exponent, Optional<Double> residuo) {
        return (s, e, t) -> new Simple2DVector(
            s.xComponent() + (e.xComponent() - s.xComponent()) * 
                (residuo.orElse(NULL_ALGEBRIC_VALUE) * residuo.map(sign -> -UNIT).orElse(UNIT) 
                + Math.min(Math.pow(base.apply(t), exponent), UNIT)), 
            s.yComponent() + (e.yComponent() - s.yComponent()) * 
                (residuo.orElse(NULL_ALGEBRIC_VALUE) * residuo.map(sign -> -UNIT).orElse(UNIT) 
                + Math.min(Math.pow(base.apply(t), exponent), UNIT))
        );
    }

    @Override
    public Interpolator<Vector2D> linear() {
        return templateEase(UnaryOperator.identity(), LINEAR_EXPO, Optional.empty());
    }

    @Override
    public Interpolator<Vector2D> easeIn() {
        return templateEase(UnaryOperator.identity(), EASY_IN_EXPO, Optional.empty());
    }

    @Override
    public Interpolator<Vector2D> easeOut() {
        return templateEase(t -> 1 - t, EASY_OUT_EXPO, Optional.of(UNIT));
    }

    @Override
    public Interpolator<Vector2D> easeInOut() {
        return templateEase(t -> 1 - t, 2.d, Optional.of(UNIT));
    }

    
    
}
