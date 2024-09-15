package com.project.paradoxplatformer.utils.geometries.vector.api;

public sealed class Simple2DVector extends AbstractVector permits Polar2DVector {

    public Simple2DVector(double x, double y) {
        super(x, y);
    }

    @Override
    public Vector2D add(Vector2D vector) {
        return new Simple2DVector(
                vector.xComponent() + this.xComponent(),
                vector.yComponent() + this.yComponent());
    }

    @Override
    public Vector2D scalar(double scalar) {
        return new Polar2DVector(scalar * this.magnitude(), this.direction());
    }

    @Override
    public double yComponent() {
        return this.cartesian.getY();
    }

    @Override
    public double xComponent() {
        return this.cartesian.getX();
    }

    @Override
    public Vector2D sub(Vector2D vector) {
        return new Simple2DVector(
                -vector.xComponent() + this.xComponent(),
                -vector.yComponent() + this.yComponent());
    }

}
