package com.project.paradoxplatformer.utils.collision.api;

import com.project.paradoxplatformer.utils.world.Dimension;
import com.project.paradoxplatformer.utils.world.Coord2D;

public interface Collidable {

    Coord2D getPosition();

    Dimension getDimension();

    void handleCollision(Collidable other);
}