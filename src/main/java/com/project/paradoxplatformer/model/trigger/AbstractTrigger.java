package com.project.paradoxplatformer.model.trigger;

import java.util.ArrayList;
import java.util.List;

import com.project.paradoxplatformer.model.entity.AbstractTrasformableObject;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public abstract class AbstractTrigger extends AbstractTrasformableObject implements Trigger {

    protected final List<Obstacle> associatedObstacles = new ArrayList<>();

    protected AbstractTrigger(final Coord2D position, final Dimension dimension) {
        super(position, dimension);
    }

    @Override
    public void updateState(final long dt) {
        this.setPosition(new Coord2D(this.displacement.xComponent(), this.displacement.yComponent()));
        this.setDimension(new Dimension(this.widthVector.magnitude(), this.heightVector.yComponent()));
    }

    @Override
    public void activate() {
        this.associatedObstacles.forEach(Obstacle::execute);
    }

    @Override
    public void addObstacle(final Obstacle obstacle) {
        this.associatedObstacles.add(obstacle);
    };

}
