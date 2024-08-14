package com.project.paradoxplatformer.model.obstacles.abstracts;

import com.project.paradoxplatformer.model.entity.AbstractTrasformableObject;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public abstract class AbstractObstacle extends AbstractTrasformableObject implements Obstacle {

    private final CollisionType TYPE = CollisionType.OBSTACLE;

    // position inevitablly immutable expept for static purpose
    protected Dimension dimension;
    protected Coord2D position;

    protected AbstractObstacle(final Coord2D position, final Dimension dimension) {
        super(position, dimension);
    }

    @Override
    public Coord2D getPosition() {
        return this.position;
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    @Override
    public void updateState(final long dt) {
        this.setPosition(new Coord2D(this.displacement.xComponent(), this.displacement.yComponent()));
        this.setDimension(new Dimension(this.widthVector.magnitude(), this.heightVector.yComponent()));
    }

    private void setDimension(final Dimension dimension) {
        this.dimension = dimension;
    }

    private void setPosition(final Coord2D position) {
        this.position = position;
    }

    @Override
    public abstract boolean isCollectable();

    @Override
    public CollisionType getCollisionType() {
        return this.TYPE;
    }
}
