package com.project.paradoxplatformer.model.trigger;

import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public abstract class AbstractTrigger implements Trigger {

    private final CollisionType type = CollisionType.TRIGGER;
    protected Coord2D position;
    protected Dimension dimension;

    public Coord2D getPosition() {
        return position;
    }

    @Override
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
        return type;
    }

    @Override
    public void updateState(long dt) {
        // TODO
    }

}
