package com.project.paradoxplatformer.model.obstacles;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * A Coin is a collectable object that a player can collect and store in the inventory.
 * @see com.project.paradoxplatformer.model.effect.impl.CollectingEffect
 */
public final class Coin extends AbstractObstacle implements CollectableGameObject {

    /**
     * Constructs a Coin based on id, position, dimension, and a queue of trajectories (upon activation).
     * @param key             The unique identifier for the coin.
     * @param position        The position of the coin.
     * @param dimension       The dimension of the coin.
     * @param trajectoryQueue The queue of trajectories associated with the coin upon activation.
     */
    public Coin(
            final int key,
            final Coord2D position,
            final Dimension dimension,
            final Queue<TrajectoryInfo> trajectoryQueue
    ) {
        super(key, position, dimension, trajectoryQueue);
    }

    /**
     * Constructs a Coin based on id, position, and dimension.
     * @param key       The unique identifier for the coin.
     * @param position  The position of the coin.
     * @param dimension The dimension of the coin.
     */
    public Coin(final int key, final Coord2D position, final Dimension dimension) {
        super(key, position, dimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionType getCollisionType() {
        return CollisionType.COLLECTING;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "Coin";
    }
}
