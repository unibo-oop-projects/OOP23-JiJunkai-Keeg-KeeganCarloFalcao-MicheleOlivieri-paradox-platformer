package com.project.paradoxplatformer.utils.geometries.vector;

import com.project.paradoxplatformer.utils.geometries.coordinates.api.Polar;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public class Polar2DVector extends Simple2DVector{

    public Polar2DVector(double r, double theta) {
        super(new Polar(r, theta).toCartesian().getX(), new Polar(r, theta).toCartesian().getY());
    }

    public static Vector2D nullVector() {
        return new Polar2DVector(0.0d, 0.0d);
    } 
    
}
