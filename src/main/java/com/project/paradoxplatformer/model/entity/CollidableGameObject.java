package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class CollidableGameObject implements GameObject, Collidable {

    @Override
    public CollisionType getCollisionType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCollisionType'");
    }

    @Override
    public boolean checkCollision(Collidable other) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkCollision'");
    }

    @Override
    public Coord2D getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public Dimension getDimension() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDimension'");
    }

}
