package com.project.paradoxplatformer.model.obstacles;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public final class DeathCoin extends AbstractObstacle {

    /**
     * Constructs a death coin based on id, position, dimension and queue of
     * trajectories (upon activation).
     * 
     * @param key             of a death coin
     * @param position        of a death coin
     * @param dimension       of a death coin
     * @param trajectoryQueue of a death coin
     */
    public DeathCoin(
            final int key,
            final Coord2D position,
            final Dimension dimension,
            final Queue<TrajectoryInfo> trajectoryQueue) {
        super(key, position, dimension, trajectoryQueue);
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.DEATH_OBS;
    }

}
