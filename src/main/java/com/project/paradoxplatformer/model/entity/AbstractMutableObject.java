package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public abstract class AbstractMutableObject implements MutableObject{

    private int key;

    @Override
    public abstract  Coord2D getPosition();

    @Override
    public abstract Dimension getDimension();

    @Override
    public abstract void setPosition(Coord2D position);

    @Override
    public abstract CollisionType getCollisionType();

    @Override
    public abstract void updateState(long dt);

    @Override
    public abstract void setDimension(Dimension dimension);

    @Override
    public abstract Vector2D getSpeed();

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public int getID() {
        return this.key;
    }
    
}
