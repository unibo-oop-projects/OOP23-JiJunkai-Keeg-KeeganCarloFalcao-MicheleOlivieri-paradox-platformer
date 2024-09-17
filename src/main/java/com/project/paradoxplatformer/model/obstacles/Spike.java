package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractDeathObstacle;

import java.util.Optional;
import java.util.Queue;

public class Spike extends AbstractDeathObstacle {

    public Spike(final int key, Coord2D position, Dimension dimension, final Queue<TrajectoryInfo> trajectoryQueue) {
        super(key, position, dimension, trajectoryQueue); // Puoi specificare il danno se necessario
    }

    @Override
    protected void triggerExplosion() {
        // Implementazione specifica per Spike
        System.out.println("Spike triggered!");
    }

    @Override
    public void onCollision(Optional<ControllableObject> ob) {
        super.onCollision(ob);
        // Eventuali comportamenti aggiuntivi specifici per Spike
        System.out.println("Spike effect applied.");
    }
}
