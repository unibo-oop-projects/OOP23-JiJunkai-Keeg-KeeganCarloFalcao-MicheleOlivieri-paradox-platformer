package com.project.paradoxplatformer.model.obstacles;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * When a player stumble upon it he performs a powerful jump (the effects is defined in {@link com.project.paradoxplatformer.model.effect.impl.SpringEffect})
 */
public final class Spring extends AbstractObstacle {

    /**
     * Constructs a spring based on id, position, dimension and queue of trajectories (upon activation).
     * @param key of spring
     * @param position of a spring
     * @param dimension of a spring
     * @param trajectoryQueue of a spring
     */
    public Spring(
        final int key,
        final Coord2D position,
        final Dimension dimension,
        final Queue<TrajectoryInfo> trajectoryQueue
    ) {
        super(key, position, dimension, trajectoryQueue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionType getCollisionType() {
        return CollisionType.SPRINGS;
    }
    
}
