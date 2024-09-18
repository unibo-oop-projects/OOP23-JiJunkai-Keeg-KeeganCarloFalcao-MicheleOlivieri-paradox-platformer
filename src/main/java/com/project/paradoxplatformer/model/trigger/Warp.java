package com.project.paradoxplatformer.model.trigger;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Represents a visible Warp in the game, typically used for teleporting
 * purposes or ing teleportation effects.
 * 
 * <p>
 * This class extends {@link AbstractTrigger} and defines a warp's position
 * and size. When activated, the warp can teleport the player or affect
 * certain gameplay elements. The warp is treated as a collidable object with
 * the collision
 * type {@link CollisionType#WARP}.
 * </p>
 */
public class Warp extends AbstractTrigger {

    /**
     * Constructs a Warp with the given position, size, id, and the trajectory
     * queue.
     * 
     * @param key             The unique id of the Warp.
     * @param position        The position of the Warp in 2D space.
     * @param dimension       The size (width and height) of the Warp.
     * @param trajectoryQueue The trajectory queue of the Warp.
     */
    public Warp(
            final int key,
            final Coord2D position,
            final Dimension dimension,
            final Queue<TrajectoryInfo> trajectoryQueue) {
        super(key, position, dimension, trajectoryQueue);
    }

    /**
     * Constructs a Warp with the given position, size, and id.
     * 
     * @param key       The unique id of the Warp.
     * @param position  The position of the Warp in 2D space.
     * @param dimension The size (width and height) of the Warp.
     */
    public Warp(final int key, final Coord2D position, final Dimension dimension) {
        super(key, position, dimension);
    }

    /**
     * Returns the collision type associated with this Warp.
     * 
     * @return The collision type, which is {@link CollisionType#WARP}.
     */
    @Override
    public CollisionType getCollisionType() {
        return CollisionType.WARP;
    }
}
