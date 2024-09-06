package com.project.paradoxplatformer.model.obstacles.abstracts;

import com.project.paradoxplatformer.model.entity.AbstractTrasformableObject;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public abstract class AbstractObstacle extends AbstractTrasformableObject implements Obstacle {

    protected AbstractObstacle(final Coord2D position, final Dimension dimension) {
        super(position, dimension);
    }

    @Override
    public void updateState(final long dt) {
        this.setPosition(new Coord2D(this.displacement.xComponent(), this.displacement.yComponent()));
        this.setDimension(new Dimension(this.widthVector.magnitude(), this.heightVector.yComponent()));
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.OBSTACLE;
    }

}
