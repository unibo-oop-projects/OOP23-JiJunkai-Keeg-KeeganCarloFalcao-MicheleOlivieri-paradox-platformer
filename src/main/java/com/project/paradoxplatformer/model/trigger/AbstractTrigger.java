package com.project.paradoxplatformer.model.trigger;

import com.project.paradoxplatformer.model.entity.AbstractTrasformableObject;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public abstract class AbstractTrigger extends AbstractTrasformableObject implements Trigger {

    protected AbstractTrigger(Coord2D position, Dimension dimension) {
        super(position, dimension);
    }

    private final CollisionType type = CollisionType.TRIGGER;
    protected Coord2D position;
    protected Dimension dimension;

    @Override
    public CollisionType getCollisionType() {
        return type;
    }

    @Override
    public void updateState(long dt) {

    };

}
