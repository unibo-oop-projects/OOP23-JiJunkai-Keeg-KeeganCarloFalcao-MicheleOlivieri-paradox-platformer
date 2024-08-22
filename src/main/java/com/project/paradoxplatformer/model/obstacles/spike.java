package main.java.com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class Spike extends AbstractDeathObstacle {

    public Spike(Coord2D position, Dimension dimension) {
        super(position, dimension); // Puoi specificare il danno se necessario
    }

    @Override
    protected void triggerExplosion() {
        // Implementazione specifica per Spike
        System.out.println("Spike triggered!");
    }

    @Override
    public void effect(Optional<ControllableObject> ob) {
        super.effect(ob);
        // Eventuali comportamenti aggiuntivi specifici per Spike
        System.out.println("Spike effect applied.");
    }
}
