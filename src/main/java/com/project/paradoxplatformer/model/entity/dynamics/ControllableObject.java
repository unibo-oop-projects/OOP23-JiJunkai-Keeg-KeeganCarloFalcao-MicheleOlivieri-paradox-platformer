package com.project.paradoxplatformer.model.entity.dynamics;

import com.project.paradoxplatformer.model.entity.dynamics.behavior.JumpBehavior;

/**
 * Represents an object that can be controlled and moved in both horizontal
 * and vertical directions within the game.
 * <p>
 * This interface extends {@link HorizontalObject} and {@link VerticalObject}
 * to include movement capabilities in both directions. It also provides methods
 * for handling jump behavior, collisions, and stopping falling actions.
 * </p>
 */
public interface ControllableObject extends HorizontalObject, VerticalObject {

    /**
     * Sets the behavior to be used when the object performs a jump.
     * 
     * @param jb the {@link JumpBehavior} to be applied during a jump
     */
    void setJumpBehavior(JumpBehavior jb);

    /**
     * Stops the falling action of the object.
     * This method should be called to stop any ongoing falling motion.
     */
    void stopFall();
}
