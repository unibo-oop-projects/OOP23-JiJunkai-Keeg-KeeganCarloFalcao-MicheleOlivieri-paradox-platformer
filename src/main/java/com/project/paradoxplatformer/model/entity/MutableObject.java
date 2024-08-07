package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public interface MutableObject extends CollidableGameObject {

    public Vector2D getSpeed();

    public void updateState(final long dt);

}
