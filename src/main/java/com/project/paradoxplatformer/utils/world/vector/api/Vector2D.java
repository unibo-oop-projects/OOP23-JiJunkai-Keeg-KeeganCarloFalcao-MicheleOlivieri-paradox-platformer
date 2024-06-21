package com.project.paradoxplatformer.utils.world.vector.api;

public interface Vector2D {
    
    double magnitude();

    double direction();

    double yComponent();

    double xComponent();

    Vector2D add(Vector2D vector);

    Vector2D scalar(double scalar);

    void setMag(double magnitude, double limit);

    Vector2D sub(Vector2D e);
    
}
