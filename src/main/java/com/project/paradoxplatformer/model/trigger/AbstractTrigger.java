package com.project.paradoxplatformer.model.trigger;

import java.util.ArrayList;
import java.util.List;

import com.project.paradoxplatformer.model.entity.AbstractTrasformableObject;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * An abstract class representing a trigger in the game.
 * This class extends AbstractTransformableObject and implements the Trigger
 * interface.
 * It provides a basic implementation for triggers that can activate associated
 * obstacles.
 */
public abstract class AbstractTrigger extends AbstractTrasformableObject implements Trigger {

    // A list of obstacles associated with this trigger. When the trigger is
    // activated, all these obstacles will be affected.
    protected final List<Obstacle> associatedObstacles = new ArrayList<>();

    /**
     * Constructs a new AbstractTrigger with the specified position and dimension.
     *
     * @param position  The initial position of the trigger.
     * @param dimension The initial dimension (size) of the trigger.
     */
    protected AbstractTrigger(final Coord2D position, final Dimension dimension) {
        super(position, dimension);
    }

    /**
     * Updates the state of the trigger. This method adjusts the position and
     * dimension of the trigger
     * based on its displacement and size vectors.
     *
     * @param dt The delta time since the last update, used for any time-dependent
     *           calculations.
     */
    @Override
    public void updateState(final long dt) {
        // Update the position of the trigger based on its displacement vector.
        this.setPosition(new Coord2D(this.displacement.xComponent(), this.displacement.yComponent()));
        // Update the dimension of the trigger based on its width and height vectors.
        this.setDimension(new Dimension(this.widthVector.magnitude(), this.heightVector.yComponent()));
    }

    /**
     * Activates the trigger. This method iterates through the list of associated
     * obstacles and executes
     * each one. The exact action taken by each obstacle will be defined in their
     * specific implementations.
     */
    @Override
    public void activate() {
        this.associatedObstacles.forEach(Obstacle::execute);
    }

    /**
     * Adds an obstacle to the list of obstacles associated with this trigger. When
     * the trigger is activated,
     * this obstacle will be affected.
     *
     * @param obstacle The obstacle to be associated with this trigger.
     */
    @Override
    public void addObstacle(final Obstacle obstacle) {
        this.associatedObstacles.add(obstacle);
    }
}
