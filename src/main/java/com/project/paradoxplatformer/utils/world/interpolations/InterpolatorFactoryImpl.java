package com.monkeytd.model;

import com.monkeytd.model.vectors.Vector2D;

public class InterpolatorFactoryImpl implements InterpolatorFactory{

    @Override
    public Interpolator<Vector2D> linear() {
        return (s, e, t) -> new Simple2DVector(
            s.xComponent() + (e.xComponent() - s.xComponent()) * Math.min(t, 1.0), 
            s.yComponent() + (e.yComponent() - s.yComponent()) * Math.min(t, 1.0));
    }

    @Override
    public Interpolator<Vector2D> easeIn() {
        return (s, e, t) -> new Simple2DVector(
            s.xComponent() + (e.xComponent() - s.xComponent()) * Math.min(Math.pow(t, 3d), 1.0), 
            s.yComponent() + (e.yComponent() - s.yComponent()) * Math.min(Math.pow(t, 3d), 1.0));
    }

    @Override
    public Interpolator<Vector2D> easeOut() {
        return (s, e, t) -> new Simple2DVector(
            s.xComponent() + (e.xComponent() - s.xComponent()) * (1 - Math.min(Math.pow(1 - t, 2d), 1.0)), 
            s.yComponent() + (e.yComponent() - s.yComponent()) * (1 - Math.min(Math.pow(1 - t, 2d), 1.0)));
    }

    @Override
    public Interpolator<Vector2D> easeInOut() {
        return (s, e, t) -> new Simple2DVector(
            s.xComponent() + (e.xComponent() - s.xComponent()) * 
                (1 - Math.min(Math.pow(1 - t, 2d), 1.0)), 
            s.yComponent() + (e.yComponent() - s.yComponent()) * 
                (1 - Math.min(Math.pow(1 - t, 2d), 1.0)));
    }

    
    
}
