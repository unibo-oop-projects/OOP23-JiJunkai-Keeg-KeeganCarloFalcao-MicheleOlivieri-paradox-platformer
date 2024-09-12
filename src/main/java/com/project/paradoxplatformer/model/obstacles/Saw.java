package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractDeathObstacle;

import java.util.Optional;

public class Saw extends AbstractDeathObstacle {

    public Saw(Coord2D position, Dimension dimension) {
        super(position, dimension);
    }

    @Override
    protected void triggerExplosion() {
        // Implementazione specifica per Saws
        System.out.println("Saw triggered!");
    }

    @Override
    public void onCollision(Optional<ControllableObject> ob) {
        super.onCollision(ob);
        // Eventuali comportamenti aggiuntivi specifici per Saws
        System.out.println("Saw effect applied.");
    }
}
