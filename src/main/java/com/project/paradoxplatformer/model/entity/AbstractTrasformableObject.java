package com.project.paradoxplatformer.model.entity;

import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public abstract class AbstractTrasformableObject extends AbstractPositionableObject{

    protected Dimension dimension;
    protected Vector2D heightVector;
    protected Vector2D widthVector;

    protected AbstractTrasformableObject(final Coord2D position, final Dimension dimension) {
        super(position);
        this.dimension = dimension;
        this.heightVector = new Simple2DVector(0.d, dimension.height());
        this.widthVector = new Simple2DVector(dimension.width(), 0.);
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    @Override
    public void setPosition(Coord2D position) {
        this.position = position;
    }

    @Override
    public void setDimension(final Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public abstract CollisionType getCollisionType();

    @Override
    public abstract void updateState(long dt);

    @Override
    public Vector2D getSpeed() {
        return Polar2DVector.nullVector();
    }
    

    
}
