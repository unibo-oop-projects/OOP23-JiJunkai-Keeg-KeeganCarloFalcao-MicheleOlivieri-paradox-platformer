package com.project.paradoxplatformer.model.obstacles.abstracts;

import com.project.paradoxplatformer.controller.event.EventManager;
import com.project.paradoxplatformer.controller.event.GameEventType;
import com.project.paradoxplatformer.model.entity.AbstractTransformableObject;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

public abstract class AbstractObstacle extends AbstractTransformableObject implements Obstacle {

    protected AbstractObstacle(final Coord2D position, final Dimension dimension) {
        super(position, dimension);
    }

    @Override
    public void updateState(final long dt) {
        this.setPosition(new Coord2D(this.displacement.xComponent(), this.displacement.yComponent()));
        this.setDimension(new Dimension(this.widthVector.magnitude(), this.heightVector.yComponent()));
    }

    public abstract CollisionType getCollisionType();

    @Override
    public void execute() {
        EventManager.getInstance().publish(GameEventType.TRIGGER_EFFECT, PageIdentifier.GAME, this);
    }

}
