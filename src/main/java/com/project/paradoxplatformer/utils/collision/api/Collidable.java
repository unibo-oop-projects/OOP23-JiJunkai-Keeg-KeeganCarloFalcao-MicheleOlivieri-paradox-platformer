package com.project.paradoxplatformer.utils.collision.api;

import com.project.paradoxplatformer.utils.world.Dimension;
import com.project.paradoxplatformer.utils.world.Point;

public interface Collidable {

    Point getPosition();

    Dimension getSize();

    void handleCollision(Collidable other);
}