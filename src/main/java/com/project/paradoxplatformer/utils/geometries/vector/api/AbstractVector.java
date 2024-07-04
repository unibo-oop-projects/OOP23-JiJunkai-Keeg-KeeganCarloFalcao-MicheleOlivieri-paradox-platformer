package com.project.paradoxplatformer.utils.geometries.vector.api;

import com.project.paradoxplatformer.utils.geometries.coordinates.api.Cartesian;

public abstract class AbstractVector implements Vector2D{

    protected Cartesian cartesian;
    private double magnitude;
    private double angle;

    protected AbstractVector(double x, double y) {
        this.cartesian = new Cartesian(x, y);
        this.magnitude = cartesian.toPolar().getMagnitude();
        this.angle = cartesian.toPolar().getAngle();
    }

    public double magnitude() {
        return this.magnitude;
    }

    public double direction() {
        return this.angle;
    }

    public abstract Vector2D add(Vector2D vector);

    public abstract Vector2D scalar(double scalar);

    public abstract double yComponent();

    public abstract double xComponent();


    @Override
    public String toString() {
        return "AbstractVector [pol=" + cartesian + "]";
    }

    
}
