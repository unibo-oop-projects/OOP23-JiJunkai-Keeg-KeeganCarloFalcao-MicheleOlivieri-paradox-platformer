package com.project.paradoxplatformer.model.obstacles.abstracts;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.AbstractTrasformableObject;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * An abstract obstacle is distinguished from other game objects by its ability
 * to be triggered, having special effects upon activation.
 * <p>
 * This abstract class simplifies the implementation of basic actions for all
 * its subclasses.
 * </p>
 */
public abstract class AbstractObstacle extends AbstractTrasformableObject implements Obstacle {

    /**
     * Constructs an abstract obstacle with trajectory information for activation
     * effects.
     * 
     * @param key             The unique identifier for the obstacle.
     * @param position        The position of the obstacle.
     * @param dimension       The dimension of the obstacle.
     * @param trajectoryQueue The queue of trajectories associated with the obstacle
     *                        upon activation.
     */
    protected AbstractObstacle(
            final int key,
            final Coord2D position,
            final Dimension dimension,
            final Queue<TrajectoryInfo> trajectoryQueue) {
        super(key, position, dimension, trajectoryQueue);
    }

    /**
     * Constructs an abstract obstacle with basic game object parameters.
     * 
     * @param key       The unique identifier for the obstacle.
     * @param position  The position of the obstacle.
     * @param dimension The dimension of the obstacle.
     */
    protected AbstractObstacle(
            final int key,
            final Coord2D position,
            final Dimension dimension) {
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

    /**
     * Gets the collision type for the obstacle.
     * 
     * @return The collision type.
     */
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
