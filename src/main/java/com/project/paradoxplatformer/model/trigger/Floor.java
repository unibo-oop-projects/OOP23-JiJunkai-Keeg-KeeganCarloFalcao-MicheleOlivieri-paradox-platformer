package com.project.paradoxplatformer.model.trigger;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Represents an invisible trigger in the game, often used for detecting when
 * objects interact with the floor.
 * This class extends AbstractTrigger, and its main purpose is to function as
 * a trigger without any visible components. It can be used for handling events
 * related to floor interactions, like player landing or stepping on it.
 */
public class Floor extends AbstractTrigger {

    /**
     * Constructs a Floor with the given position, size, id and the trajectory queue.
     * 
     * @param key  The unique id of the Floor
     * @param position  the position of the Floor in 2D space
     * @param dimension the size (width and height) of the Floor
     * @param trajectoryQueue of the Floor
     */
    public Floor(
        final int key,
        final Coord2D position, 
        final Dimension dimension, 
        final Queue<TrajectoryInfo> trajectoryQueue
        ) {
        super(key, position, dimension, trajectoryQueue);
    }

    /**
     * Constructs a Floor with the given position, size and id.
     * 
     * @param key The unique id of the Floor
     * @param position  the position of the Floor in 2D space
     * @param dimension the size (width and height) of the Floor
     */
    public Floor(final int key, final Coord2D position, final Dimension dimension) {
        super(key, position, dimension);
    }

    /**
     * Returns the type of collision this trigger represents, which is "FLOOR".
     *
     * @return the collision type as FLOOR
     */
    @Override
    public CollisionType getCollisionType() {
        return CollisionType.FLOOR;
    }
}
