package com.project.paradoxplatformer.utils.entity;


import com.project.paradoxplatformer.utils.world.vector.api.Vector2D;

public interface MutableObject extends GameObject{

    public Vector2D getSpeed();

    public void updateState(long dt);

}
