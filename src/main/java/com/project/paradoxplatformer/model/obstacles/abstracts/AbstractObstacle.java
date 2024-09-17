package com.project.paradoxplatformer.model.obstacles.abstracts;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.AbstractTrasformableObject;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * An abstract obstacle is distinguished from other game objects by the abily to be triggered, so they have special effects upon activation.
 * <p>Such abstract class simplify all implemented subclasses for basic action</p>
 */
public abstract class AbstractObstacle extends AbstractTrasformableObject implements Obstacle {

    /**
     * A contructor with trajectory infos (for activation effects).
     * @param key unique id
     * @param position of the game object
     * @param dimension of the game object
     * @param trajectoryQueue of the game object, all the trajectory moves it does when activated
     */
    protected AbstractObstacle(final int key, final Coord2D position, final Dimension dimension, final Queue<TrajectoryInfo> trajectoryQueue) {
        super(key, position, dimension, trajectoryQueue);
    }

    /**
     * A constructor with basic game objects parameters.
     * @param key unique id
     * @param position of the game object
     * @param dimension of the game object
     */
    protected AbstractObstacle(final int key, final Coord2D position, final Dimension dimension) {
        super(key, position, dimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateState(final long dt) {
        super.updateState(dt);
        this.setPosition(new Coord2D(this.displacement.xComponent(), this.displacement.yComponent()));
        this.setDimension(new Dimension(this.widthVector.magnitude(), this.heightVector.yComponent()));
    }

    public abstract CollisionType getCollisionType();

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        System.out.println("GAME OBJECT TRIGGERED");
        this.isIdle = false;
    }

}
