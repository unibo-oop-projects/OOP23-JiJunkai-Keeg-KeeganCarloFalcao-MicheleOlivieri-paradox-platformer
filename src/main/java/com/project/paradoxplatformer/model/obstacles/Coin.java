package com.project.paradoxplatformer.model.obstacles;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * A Coin game object is a collectable object which a player can collect and store in the inventory.
 * @see com.project.paradoxplatformer.model.effect.impl.CollectingEffect
 */
public final class Coin extends AbstractObstacle implements CollectableGameObject{

    /**
     * Constructs a coin based on id, position, dimension and queue of trajectories (upon activation).
     * @param key of a coin
     * @param position of a coin
     * @param dimension of a coin
     * @param trajectoryQueue of a coin
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
     * {@inheritDoc}
     */
    public Coin(final int key, Coord2D position, Dimension dimension) {
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
