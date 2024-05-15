package com.project.paradoxplatformer.utils.entity;

import com.project.paradoxplatformer.utils.world.Vector;

public interface MutableObject extends GameObject {

    Vector getSpeed();

    void update(double dt);

}
