package com.project.paradoxplatformer.model.obstacles;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Represents a spring that performs a powerful jump when a player encounters it.
 * The effect is defined in {@link com.project.paradoxplatformer.model.effect.impl.SpringEffect}.
 */
public final class Spring extends AbstractObstacle {

    /**
     * Constructs a spring with the specified parameters.
     *
     * @param key             The unique identifier for the spring.
     * @param position        The position of the spring.
     * @param dimension       The dimension of the spring.
     * @param trajectoryQueue The queue of trajectories associated with the spring upon activation.
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
