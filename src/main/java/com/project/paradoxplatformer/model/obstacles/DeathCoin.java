package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractDeathObstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public final class DeathCoin extends AbstractDeathObstacle {

    public DeathCoin(Coord2D position, Dimension dimension) {
        super(position, dimension);
    }

    @Override
    protected void triggerExplosion() {
        // Implementa la logica di esplosione (effetti grafici, suoni, ecc.)
    }

    @Override
    public void inflictDamage(ControllableObject player) {
        super.inflictDamage(player);
        // possiamo aggiungere ulteriori logiche specifiche per DeathCoin, se necessario
    }

    public Vector2D getSpeed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSpeed'");
    }
}
