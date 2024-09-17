package com.project.paradoxplatformer.model.entity.dynamics.abstracts;

import com.project.paradoxplatformer.model.entity.AbstractMutableObject;
import com.project.paradoxplatformer.model.entity.dynamics.HorizontalObject;
import com.project.paradoxplatformer.utils.geometries.physic.Direction;
import com.project.paradoxplatformer.utils.geometries.vector.api.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * Abstract base class for objects that move horizontally. This class
 * extends {@link AbstractMutableObject} and implements
 * {@link HorizontalObject}.
 * It provides methods to move left, right, and stop, while managing the
 * object's horizontal speed and direction.
 * <p>
 * The movement is controlled using a magnitude that adjusts based on
 * the object's speed and direction. The object will stop if the magnitude
 * reaches zero.
 * </p>
 */
public abstract class AbstractHorizontalObject extends AbstractMutableObject implements HorizontalObject {

    // Constants for movement direction
    private static final int LEFT_MAG_SIGN = -1;
    private static final int RIGHT_MAG_SIGN = 1;
    private static final double RESET_MAG = 0.0;
    private static final double NO_ADDINGS = 0.0;

    private final double limit;
    private final double delta;
    protected double magnitude;
    protected Vector2D horizontalSpeed;
    private Direction currentDirection;
    private boolean isJumping;


     /**
     * Constructs an {@code AbstractHorizontalObject} with the specified
     * limit and delta values.
     * 
     * @param limit the maximum magnitude of movement
     * @param delta the amount of magnitude change per movement
     */
    protected AbstractHorizontalObject(final int key, final double limit, final double delta) {
        super(key);
        this.magnitude = RESET_MAG;
        this.delta = delta;
        this.limit = limit;
        this.currentDirection = Direction.RIGHT;
    }

        
   

    /**
     * Gets the base delta value used for adjusting movement speed.
     * 
     * @return the base delta value
     */
    @Override
    public double getBaseDelta() {
        return this.delta;
    }

    /**
     * Handles the movement behavior based on the specified direction and
     * magnitude sign.
     * 
     * @param movingDir     the direction in which to move
     * @param magnitudeSign the sign of the magnitude for movement
     */
    private void moveBehaviour(final Direction movingDir, final double magnitudeSign) {
        this.currentDirection = movingDir;
        if (movingDir.getStatus()) {
            this.magnitude = RESET_MAG;
        }
        this.magnitude += this.magnitude > this.limit ? NO_ADDINGS : this.delta;

        // Update horizontal speed using polar coordinates
        this.horizontalSpeed = new Polar2DVector(this.magnitude * magnitudeSign, 0.0);

        movingDir.setStatus(false);
        movingDir.opposite().setStatus(true);
    }

    /**
     * Moves the object to the left.
     */
    @Override
    public void moveLeft() {
        this.moveBehaviour(Direction.LEFT, LEFT_MAG_SIGN);
    }

    /**
     * Moves the object to the right.
     */
    @Override
    public void moveRight() {
        this.moveBehaviour(Direction.RIGHT, RIGHT_MAG_SIGN);
    }

    /**
     * Stops the object's horizontal movement.
     */
    @Override
    public void stop() {
        this.magnitude -= this.magnitude > 0 ? delta : 0.0;
        this.horizontalSpeed = new Polar2DVector(
                this.magnitude * (horizontalSpeed.xComponent() >= 0.0 ? 1 : -1),
                0.0);
        this.horizontalSpeed = Polar2DVector.nullVector();
    }

    /**
     * Gets the current direction of the object.
     * 
     * @return the current direction
     */
    @Override
    public Direction direction() {
        return this.currentDirection;
    }

    // ALTERNATIVE
    // In moveBehaviour, use two boolean parameters, respectively moveRight and
    // moveLeft
    // Will simplify implementation, declare them in constructor and as fields
}
