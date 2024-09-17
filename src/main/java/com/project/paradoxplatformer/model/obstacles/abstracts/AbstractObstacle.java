package com.project.paradoxplatformer.model.obstacles.abstracts;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.AbstractTrasformableObject;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public abstract class AbstractObstacle extends AbstractTrasformableObject implements Obstacle {

    protected AbstractObstacle(final int key, final Coord2D position, final Dimension dimension, final Queue<TrajectoryInfo> trajectoryQueue) {
        super(key, position, dimension, trajectoryQueue);
    }

    protected AbstractObstacle(final int key, final Coord2D position, final Dimension dimension) {
        super(key, position, dimension);
    }

    @Override
    public void updateState(final long dt) {
        super.updateState(dt);
        this.setPosition(new Coord2D(this.displacement.xComponent(), this.displacement.yComponent()));
        this.setDimension(new Dimension(this.widthVector.magnitude(), this.heightVector.yComponent()));
    }

    public abstract CollisionType getCollisionType();

    @Override
    public void execute() {
        System.out.println("GAME OBJECT TRIGGERED");
        this.isIdle = false;
    }

}
