package com.monkeytd.model;

import com.monkeytd.model.vectors.Vector2D;

public interface InterpolatorFactory {
    
    Interpolator<Vector2D> linear();

    Interpolator<Vector2D> easeIn();

    Interpolator<Vector2D> easeOut();

    Interpolator<Vector2D> easeInOut();
}
