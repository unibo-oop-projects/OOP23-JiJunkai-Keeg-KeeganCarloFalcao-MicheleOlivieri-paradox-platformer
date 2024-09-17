package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;

import java.util.Queue;

/**
 * A Saw is an harmful obstacle upon which the player may get damage
 */
public final class Saw extends AbstractObstacle {

    /**
     * {@inheritDoc}
     */
    public Saw(
            final int key,
            final Coord2D position,
            final Dimension dimension,
            final Queue<TrajectoryInfo> trajectoryQueue) {
        super(key, position, dimension, trajectoryQueue);
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.SAW;
    }

}
