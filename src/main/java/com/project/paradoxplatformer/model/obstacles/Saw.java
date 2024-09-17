package com.project.paradoxplatformer.model.obstacles;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * A Saw is a harmful obstacle upon which the player may get damaged.
 */
public final class Saw extends AbstractObstacle {

    /**
     * Constructs a Saw object with the specified parameters.
     *
     * @param key             The unique identifier for the saw.
     * @param position        The position of the saw.
     * @param dimension       The dimension of the saw.
     * @param trajectoryQueue The queue of trajectories associated with the saw upon activation.
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
