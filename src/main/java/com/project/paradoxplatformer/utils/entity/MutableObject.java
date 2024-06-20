package com.project.paradoxplatformer.utils.entity;


import com.project.paradoxplatformer.utils.world.vector.api.Vector2D;

public interface MutableObject extends GameObject{

    Vector2D getSpeed();

    void updateState(long dt);

}
