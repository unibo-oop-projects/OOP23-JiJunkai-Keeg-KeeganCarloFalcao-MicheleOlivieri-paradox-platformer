package com.project.paradoxplatformer.model.obstacles;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * When a player hits a Wall it blocks its moving (it acts as if it is a barrier)
 * @see com.project.paradoxplatformer.model.effect.impl.HorizontalBlockEffect
 */
public final class Wall extends AbstractObstacle {

    /**
     * Constructs a wall based on id, position, dimension and queue of trajectories (upon activation).
     * @param key of a wall
     * @param position of a wall
     * @param dimension of a wall
     * @param trajectoryQueue of a wall
     */
    public Wall(
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
        return CollisionType.WALLS;
    }
}
