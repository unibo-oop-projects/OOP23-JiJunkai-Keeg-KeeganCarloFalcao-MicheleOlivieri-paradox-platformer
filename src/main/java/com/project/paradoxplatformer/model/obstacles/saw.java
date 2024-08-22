package main.java.com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

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
    public void effect(Optional<ControllableObject> ob) {
        super.effect(ob);
        // Eventuali comportamenti aggiuntivi specifici per Saws
        System.out.println("Saw effect applied.");
    }
}
