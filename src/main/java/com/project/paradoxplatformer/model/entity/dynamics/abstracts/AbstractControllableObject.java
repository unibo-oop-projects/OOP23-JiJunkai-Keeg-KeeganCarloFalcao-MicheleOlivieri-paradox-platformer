package com.project.paradoxplatformer.model.entity.dynamics.abstracts;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.entity.dynamics.behavior.JumpBehavior;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * An abstract class representing a controllable object that can move
 * horizontally
 * and vertically, and interact with game events.
 * <p>
 * This class extends {@link AbstractHorizontalObject} and implements
 * {@link ControllableObject} to provide functionality for vertical movement,
 * jumping, and falling. It also supports setting up game event listeners for
 * handling collision events.
 * </p>
 * <p>
 * REMINDER: This class should extend a merged abstract class for horizontal and
 * vertical movement.
 * </p>
 */
public abstract class AbstractControllableObject extends AbstractHorizontalObject implements ControllableObject {

    private Vector2D verticalSpeed;
    private JumpBehavior jumpBehavior;
    private boolean isJumping;

    /**
     * Constructs an {@code AbstractControllableObject} with the specified initial
     * displacement and horizontal statistics.
     * 
     * @param id               unique id for the controllable object
     * @param initDisplacement the initial displacement vector for the object as a
     *                         {@link Vector2D} object
     * @param stats            the horizontal statistics to be used, encapsulated in
     *                         a {@link HorizontalStats} object
     */
    protected AbstractControllableObject(final int id, final Vector2D initDisplacement, final HorizontalStats stats) {
        super(id, stats.limit(), stats.delta());
        this.verticalSpeed = new Simple2DVector(0.0, 0.0);
        this.isJumping = false;
    }

    /**
     * Executes a jump action if the {@link JumpBehavior} is set.
     * <p>
     * The vertical speed is updated based on the jump behavior's jump vector.
     * </p>
     */
    @Override
    public void jump() {
        jumpBehavior.jump().ifPresent(vector -> {
            this.verticalSpeed = vector;
            this.isJumping = true;
        });
    }

    /**
     * Executes a fall action based on the {@link JumpBehavior}.
     * <p>
     * The vertical speed is updated to the fall vector defined in the jump
     * behavior.
     * </p>
     */
    @Override
    public void fall() {
        this.verticalSpeed = this.jumpBehavior.fall();
        this.isJumping = false;
    }

    /**
     * Sets the jump behavior for this object.
     * 
     * @param jb the {@link JumpBehavior} to be set
     */
    @Override
    public void setJumpBehavior(final JumpBehavior jb) {
        this.jumpBehavior = jb;
    }

    /**
     * Stops the falling motion by resetting the vertical speed and adjusting the
     * jump behavior state.
     * <p>
     * This method sets the vertical speed to zero, updates the jump behavior to
     * stop falling, and resets gravity.
     * </p>
     */
    @Override
    public void stopFall() {
        // Reset vertical speed to stop falling
        this.verticalSpeed = new Simple2DVector(0.0, 0.0);

        // Update jump behavior to stop falling and reset gravity
        jumpBehavior.setFalling(false);
        jumpBehavior.resetGravity();
    }

    /**
     * Gets the current vertical speed of the object.
     * 
     * @return the vertical speed vector
     */
    protected Vector2D getVerticalSpeed() {
        return verticalSpeed;
    }

    /**
     * Sets the vertical speed of the object.
     * 
     * @param verticalSpeed the new vertical speed vector
     */
    protected void setVerticalSpeed(final Vector2D verticalSpeed) {
        this.verticalSpeed = verticalSpeed;
    }

    /**
     * Gets the current jump behavior of the object.
     * 
     * @return the current {@link JumpBehavior}
     */
    protected JumpBehavior getJumpBehavior() {
        return jumpBehavior;
    }

    /**
     * Checks if the object is currently jumping.
     * 
     * @return true if the object is jumping, false otherwise
     */
    protected boolean isJumping() {
        return isJumping;
    }

    /**
     * Sets the jumping state of the object.
     * 
     * @param isJumping true if the object is jumping, false otherwise
     */
    protected void setJumping(final boolean isJumping) {
        this.isJumping = isJumping;
    }
}
