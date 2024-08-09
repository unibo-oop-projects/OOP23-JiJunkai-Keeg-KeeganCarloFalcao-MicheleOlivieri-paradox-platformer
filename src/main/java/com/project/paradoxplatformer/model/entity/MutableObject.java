package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public interface MutableObject extends Collidable {

    public Vector2D getSpeed();

    public void updateState(final long dt);

}
