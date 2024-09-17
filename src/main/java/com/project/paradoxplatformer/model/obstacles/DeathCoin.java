package com.project.paradoxplatformer.model.obstacles;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractDeathObstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public final class DeathCoin extends AbstractDeathObstacle {

    /**
     * Constructs a death coin based on id, position, dimension and queue of trajectories (upon activation).
     * @param key of a death coin
     * @param position of a death coin
     * @param dimension of a death coin
     * @param trajectoryQueue of a death coin
     */
    public DeathCoin(
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
        // Implementa la logica di esplosione (effetti grafici, suoni, ecc.)
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inflictDamage(ControllableObject player) {
        super.inflictDamage(player);
        // possiamo aggiungere ulteriori logiche specifiche per DeathCoin, se necessario
    }

    
}
