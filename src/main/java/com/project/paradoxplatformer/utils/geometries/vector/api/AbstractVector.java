package com.project.paradoxplatformer.utils.geometries.vector.api;

import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.coordinates.api.Cartesian;

public sealed abstract class AbstractVector implements Vector2D permits Simple2DVector {

    protected Cartesian cartesian;
    private double magnitude;
    private double angle;

    protected AbstractVector(double x, double y) {
        this.cartesian = new Cartesian(x, y);
        this.magnitude = cartesian.toPolar().getMagnitude();
        this.angle = cartesian.toPolar().getAngle();
    }

    @Override
    public double magnitude() {
        return this.magnitude;
    }

    @Override
    public double direction() {
        return this.angle;
    }

    @Override
    public Coord2D convert() {
        return new Coord2D(this.xComponent(), this.yComponent());
    }

    public abstract Vector2D add(Vector2D vector);

    public abstract Vector2D scalar(double scalar);

    public abstract double yComponent();

    public abstract double xComponent();

    @Override
    public String toString() {
        return "{" + cartesian + ", Mag: " + magnitude + "}";
    }

}
