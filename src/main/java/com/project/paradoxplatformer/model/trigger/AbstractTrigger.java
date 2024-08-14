package com.project.paradoxplatformer.model.trigger;

import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public abstract class AbstractTrigger implements Trigger {

    private final CollisionType TYPE = CollisionType.TRIGGER;
    protected Coord2D position;
    protected Dimension dimension;

    public Coord2D getPosition() {
        return position;
    }

    public void setPosition(Coord2D position) {
        this.position = position;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public CollisionType getCollisionType() {
        return this.TYPE;
    }

    @Override
    public void updateState(long dt) {
        // TODO
    }

    @Override
    public Vector2D getSpeed() {
        // TODO Auto-generated method stub
        return null;
    }

}
