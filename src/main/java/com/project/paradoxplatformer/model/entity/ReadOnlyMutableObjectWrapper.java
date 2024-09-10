package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public class ReadOnlyMutableObjectWrapper implements MutableObject{

    private final MutableObject wrapped;

    public ReadOnlyMutableObjectWrapper(final MutableObject abstractDecorator) {
        this.wrapped = abstractDecorator;
    }

    @Override
    public Coord2D getPosition() {
        return this.wrapped.getPosition();
    }

    @Override
    public Dimension getDimension() {
        return this.wrapped.getDimension();
    }

    @Override
    public int getID() {
        return this.wrapped.getID();
    }
    
    @Override
    public CollisionType getCollisionType() {
        return this.wrapped.getCollisionType();
    }

    @Override
    public void setPosition(Coord2D position) {
        throw new UnsupportedOperationException("Unable to execute 'setPosition'");
    }

    @Override
    public void setDimension(Dimension dimension) {
        throw new UnsupportedOperationException("Unable to execute 'setDimension'");
    }


    @Override
    public void updateState(long dt) {
        throw new UnsupportedOperationException("Unable to execute 'updateState'");
    }

    @Override
    public void setKey(int key) {
        throw new UnsupportedOperationException("Unable to execute 'setKey'");
    }

    public Vector2D getSpeed() {
        return this.wrapped.getSpeed();
    }

    @Override
    public double getBaseDelta() {
        return this.wrapped.getBaseDelta();
    }
}
