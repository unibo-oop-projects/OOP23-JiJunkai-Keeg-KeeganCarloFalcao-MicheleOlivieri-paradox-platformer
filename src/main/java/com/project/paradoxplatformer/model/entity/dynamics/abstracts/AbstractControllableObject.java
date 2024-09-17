package com.project.paradoxplatformer.model.entity.dynamics.abstracts;

import com.project.paradoxplatformer.controller.games.GameEventListener;
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
 * {@link ControllableObject}
 * to provide functionality for vertical movement, jumping, and falling. It also
 * supports
 * setting up game event listeners for handling collision events.
 * </p>
 * 
 * REMINDER this class should extend horizonal and vertical merged abstract
 * class.
 */
public abstract class AbstractControllableObject extends AbstractHorizontalObject implements ControllableObject {

    protected Vector2D verticalSpeed;
    protected JumpBehavior jumpBehavior;
    private GameEventListener gameEventListener;
    protected boolean isJumping;

    /**
     * Constructs an {@code AbstractControllableObject} with the specified initial
     * displacement
     * and horizontal statistics.
     * 
     * @param initDisplacement the initial displacement vector for the object as a
     *                         {@link Vector2D} object
     * @param stats            the horizontal statistics to be used, encapsulated in
     *                         a {@link HorizontalStats} object
     */
    protected AbstractControllableObject(final int id, final Vector2D initDisplacement, final HorizontalStats stats) {
        super(id, stats.limit(), stats.delta());
        this.verticalSpeed = new Simple2DVector(0., 0.);
        isJumping = false;
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
    }

    /**
     * Sets the jump behavior for this object.
     * 
     * @param jb the {@link JumpBehavior} to be set
     */
    @Override
    public void setJumpBehavior(JumpBehavior jb) {
        this.jumpBehavior = jb;
    }

    /**
     * Sets the game event listener for this object.
     * <p>
     * The listener will be notified when collision events occur.
     * </p>
     * 
     * @param listener the {@link GameEventListener} to be set
     */
    public void setGameEventListener(GameEventListener listener) {
        this.gameEventListener = listener;
    }

    /**
     * Handles a collision event.
     * <p>
     * If a {@link GameEventListener} is attached, it triggers the player death
     * event.
     * If no listener is attached, a message is printed indicating the absence of a
     * listener.
     * </p>
     */
    @Override
    public void onCollision() {
        if (gameEventListener != null) {
            System.out.println("Player death event triggered.");
            gameEventListener.onPlayerDeath(); // Notifies the controller of the event
        } else {
            System.out.println("No GameEventListener attached.");
        }
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
        this.verticalSpeed = new Simple2DVector(0., 0.);

        // Update jump behavior to stop falling and reset gravity
        jumpBehavior.setFalling(false);
        jumpBehavior.resetGravity();
    }
}
