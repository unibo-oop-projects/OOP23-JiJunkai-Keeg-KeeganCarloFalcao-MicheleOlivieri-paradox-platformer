package com.project.paradoxplatformer.model.obstacles.abstracts;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.abstracts.AbstractTrasformableObject;
import com.project.paradoxplatformer.model.obstacles.api.Obstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import java.util.Queue;

public abstract class AbstractObstacle extends AbstractTrasformableObject implements Obstacle {

    //position inevitablly immutable expept for static purpose
    protected Dimension dimension;
    protected Coord2D  position;

    protected AbstractObstacle(final Coord2D position, final Dimension dimension, final Queue<TrajectoryInfo> trajStats) {
        super(position, dimension, trajStats);
        this.position = position;
        this.dimension = dimension;
    }

    @Override
    public Coord2D getPosition() {
        return this.position;
    }

    @Override
    public Dimension getDimension() {
        return this.dimension;
    }

    @Override
    public void effect() {
        if(!trasformationStats.isEmpty()) {
            this.isIdle = false;
        }
    }

    @Override
    public void updateState(long dt) {
        this.position = new Coord2D(this.displacement.xComponent(), this.displacement.yComponent());
        this.dimension = new Dimension(this.widthVector.magnitude(), this.heightVector.magnitude());
    }
    
}
