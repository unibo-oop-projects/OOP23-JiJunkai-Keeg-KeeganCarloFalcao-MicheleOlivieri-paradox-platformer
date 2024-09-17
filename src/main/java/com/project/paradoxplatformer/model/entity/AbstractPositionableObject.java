package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactory;
import com.project.paradoxplatformer.utils.geometries.interpolations.InterpolatorFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.modifiers.PhysicsEngine;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public abstract class AbstractPositionableObject extends AbstractMutableObject {

    protected final PhysicsEngine mover;
    protected final InterpolatorFactory interFactory;
    protected boolean isIdle;
    protected Vector2D displacement;
    protected Coord2D position;

    protected AbstractPositionableObject(final int key, final Coord2D position) {
        super(key);
        this.isIdle = true;
        this.mover = new PhysicsEngine();
        this.interFactory = new InterpolatorFactoryImpl();
        this.displacement = new Simple2DVector(position.x(), position.y());
        this.position = position;
    }

    @Override
    public Coord2D getPosition() {
        return this.position;
    }

    @Override
    public abstract Dimension getDimension();

    @Override
    public abstract void setDimension(Dimension dimension);

    @Override
    public abstract void setPosition(Coord2D position);

    @Override
    public abstract CollisionType getCollisionType();

    @Override
    public abstract void updateState(long dt);
}
