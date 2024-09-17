package com.project.paradoxplatformer.model.obstacles;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * A DeathCoin is an obstacle that causes the player to lose the game if collided with.
 */
public final class DeathCoin extends AbstractObstacle {

    /**
     * Constructs a DeathCoin with the specified parameters.
     *
     * @param key             The unique identifier for the death coin.
     * @param position        The position of the death coin.
     * @param dimension       The dimension of the death coin.
     * @param trajectoryQueue The queue of trajectories associated with the death coin upon activation.
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
