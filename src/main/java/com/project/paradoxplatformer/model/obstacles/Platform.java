package com.project.paradoxplatformer.model.obstacles;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * A platform is type of game object where the player can ground on.
 */
public final class Platform extends AbstractObstacle{

    /**
     * Constructs a platform based on id, position, dimension and queue of trajectories (upon activation).
     * @param key of
     * @param position of
     * @param dimension of
     * @param trajectoryQueue of
     */
    public Platform(
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
        return CollisionType.PLATFORM;
    }
}
