package com.project.paradoxplatformer.utils.collision.api;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public interface Collidable {

    Coord2D getPosition();

    Dimension getDimension();

    void handleCollision(Collidable other);
}