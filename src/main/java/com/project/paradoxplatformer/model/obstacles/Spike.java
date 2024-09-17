package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractDeathObstacle;

import java.util.Optional;
import java.util.Queue;

/**
 * When a player encounters a Spike it may receive some damage
 */
public final class Spike extends AbstractDeathObstacle {

    /**
     * Constructs a spike based on id, position, dimension and queue of trajectories (upon activation).
     * @param key of a spike
     * @param position of a spike
     * @param dimension of a spike
     * @param trajectoryQueue of a spike
     */
    public Spike(
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
    protected void triggerExplosion() {
        // Implementazione specifica per Spike
        System.out.println("Spike triggered!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCollision(Optional<ControllableObject> ob) {
        super.onCollision(ob);
        // Eventuali comportamenti aggiuntivi specifici per Spike
        System.out.println("Spike effect applied.");
    }
}
