package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;
 
public abstract class AbstractHarmlessObstacle extends AbstractObstacle{

    protected AbstractHarmlessObstacle(Coord2D position, Dimension dimension) {
        super(position, dimension);
    }

    @Override
    public boolean isHarmful() {
        return false;
    }

    /**
     * 
     * By default it is not collectable, let implementation decide wether it is or not.
     */
    @Override
    public boolean isCollectable() {
        return false;
    }

    @Override
    public Vector2D getSpeed() {
        // TODO Auto-generated method stub
        return null;
    }

}
