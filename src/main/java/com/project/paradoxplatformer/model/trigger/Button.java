package com.project.paradoxplatformer.model.trigger;

import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Represents a visible Button trigger in the game, typically used for
 * interactive purposes such as triggering obstacles.
 * 
 * <p>
 * This class extends {@link AbstractTrigger} and defines a button's position
 * and size. When activated, the button can trigger actions such as affecting
 * obstacles. The button is treated as a collidable object with the collision
 * type
 * {@link CollisionType#BUTTON}.
 * </p>
 */
public class Button extends AbstractTrigger {

    /**
     * Constructs a Button with the given position, size, id and the trajectory queue.
     * 
     * @param key  The unique id of the Button
     * @param position  the position of the Button in 2D space
     * @param dimension the size (width and height) of the Button
     * @param trajectoryQueue of the Button
     */
    public Button(
        final int key,
        final Coord2D position, 
        final Dimension dimension, 
        final Queue<TrajectoryInfo> trajectoryQueue
        ){
        super(key, position, dimension, trajectoryQueue);
    }

    /**
     * Constructs a Button with the given position, size and id.
     * 
     * @param key  The unique id of the Button
     * @param position  the position of the Button in 2D space
     * @param dimension the size (width and height) of the Button
     */
    public Button(final int key, final Coord2D position, final Dimension dimension) {
        super(key, position, dimension);
    }

    /**
     * Returns the collision type associated with this button.
     * 
     * @return the collision type, which is {@link CollisionType#BUTTON}
     */
    @Override
    public CollisionType getCollisionType() {
        return CollisionType.BUTTON;
    }
}
