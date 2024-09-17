package com.project.paradoxplatformer.model.trigger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import com.project.paradoxplatformer.model.entity.AbstractTrasformableObject;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * An abstract class representing a trigger in the game.
 * This class extends {@link AbstractTransformableObject} and implements the
 * {@link Trigger} interface.
 * It provides a basic implementation for triggers that can activate associated
 * obstacles.
 */
public abstract class AbstractTrigger extends AbstractTrasformableObject implements Trigger {

    // A list of obstacles associated with this trigger. When the trigger is
    // activated, all these obstacles will be affected.
    private final List<Triggerable> associatedObstacles = new ArrayList<>();

    private Optional<Integer> triggerableID;

    /**
     * Constructs a new AbstractTrigger with the specified position and dimension.
     * 
     * @param key       The unique id of the trigger.
     * @param position  The initial position of the trigger.
     * @param dimension The initial dimension (size) of the trigger.
     */
    protected AbstractTrigger(final int key, final Coord2D position, final Dimension dimension) {
        super(key, position, dimension);
    }

    /**
     * Constructs a new AbstractTrigger with the specified position and dimension,
     * with id and trajectory queue.
     * 
     * @param key             The unique id of the trigger.
     * @param position        The initial position of the trigger.
     * @param dimension       The initial dimension (size) of the trigger.
     * @param trajectoryQueue The queue of potential trigger transformations.
     */
    protected AbstractTrigger(
            final int key,
            final Coord2D position,
            final Dimension dimension,
            final Queue<TrajectoryInfo> trajectoryQueue) {
        super(key, position, dimension, trajectoryQueue);
    }

    /**
     * Updates the state of the trigger. This method adjusts the position and
     * dimension of the trigger based on its displacement and size vectors.
     * 
     * @param dt The delta time since the last update, used for any time-dependent
     *           calculations.
     */
    @Override
    public void updateState(final long dt) {
        super.updateState(dt);
        // Update the position of the trigger based on its displacement vector.
        this.setPosition(new Coord2D(this.displacement.xComponent(), this.displacement.yComponent()));
        // Update the dimension of the trigger based on its width and height vectors.
        this.setDimension(new Dimension(this.widthVector.magnitude(), this.heightVector.yComponent()));
    }

    /**
     * Activates the trigger. This method iterates through the list of associated
     * obstacles and executes each one. The exact action taken by each obstacle
     * will be defined in their specific implementations.
     */
    @Override
    public void activate() {
        this.associatedObstacles.forEach(Triggerable::execute);
    }

    /**
     * Adds an obstacle to the list of obstacles associated with this trigger.
     * When the trigger is activated, this obstacle will be affected.
     * 
     * @param obstacle The obstacle to be associated with this trigger.
     */
    @Override
    public void addObstacle(final Triggerable obstacle) {
        this.associatedObstacles.add(obstacle);
    }

    /**
     * Gets the unique ID associated with this trigger.
     * 
     * @return An {@link Optional} containing the ID of the trigger, or an empty
     *         {@link Optional} if no ID is set.
     */
    @Override
    public Optional<Integer> getTriggerableID() {
        return this.triggerableID;
    }

    /**
     * Sets the unique ID for this trigger.
     * 
     * @param id An {@link Optional} containing the ID to set.
     */
    @Override
    public void setTriggerableID(final Optional<Integer> id) {
        this.triggerableID = id;
    }

}
