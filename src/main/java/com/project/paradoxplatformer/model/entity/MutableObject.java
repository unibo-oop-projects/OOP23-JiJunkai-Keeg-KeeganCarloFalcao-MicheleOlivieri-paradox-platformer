package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public interface MutableObject extends CollidableGameObject {

    public void updateState(final long dt);

    public void setKey(int key);

    public int getID();

    public Vector2D getSpeed();

    public double getBaseDelta();
}
