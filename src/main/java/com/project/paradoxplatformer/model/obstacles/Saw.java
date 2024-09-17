package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractDeathObstacle;

import java.util.Optional;
import java.util.Queue;

/**
 * A Saw is an harmful obstacle upon which the player may get damage
 */
public final class Saw extends AbstractDeathObstacle {

    /**
     * {@inheritDoc}
     */
    public Saw(
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
        // Implementazione specifica per Saws
        System.out.println("Saw triggered!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCollision(Optional<ControllableObject> ob) {
        super.onCollision(ob);
        // Eventuali comportamenti aggiuntivi specifici per Saws
        System.out.println("Saw effect applied.");
    }
}
