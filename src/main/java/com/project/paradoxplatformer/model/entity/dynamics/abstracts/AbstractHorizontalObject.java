package com.project.paradoxplatformer.model.entity.dynamics.abstracts;

import com.project.paradoxplatformer.model.entity.AbstractMutableObject;
import com.project.paradoxplatformer.model.entity.dynamics.HorizontalObject;
import com.project.paradoxplatformer.utils.geometries.modifiers.Direction;
import com.project.paradoxplatformer.utils.geometries.vector.api.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;


public abstract class AbstractHorizontalObject extends AbstractMutableObject implements HorizontalObject {

    //Conventional cartesian values
    private static final int LEFT_MAG_SIGN = -1;
    private static final int RIGHT_MAG_SIGN = 1;
    private static final double RESET_MAG = 0.;
    private static final double NO_ADDINGS = 0.;

    private final double limit;
    private final double delta;
    protected double magnitude;
    protected Vector2D horizontalSpeed;
    private Direction currentDirection;


    protected AbstractHorizontalObject(final double limit, final double delta) {
        this.magnitude = RESET_MAG;
        this.delta = delta;
        this.limit = limit;
        this.currentDirection = Direction.RIGHT;
    }

    @Override
    public double getBaseDelta() {
        return this.delta;
    }

    private void moveBehaviour(final Direction movingDir, final double magnitudeSign) {
        this.currentDirection = movingDir;
        if(movingDir.getStatus()) {
            this.magnitude = RESET_MAG;
        }
        this.magnitude += this.magnitude > this.limit ? NO_ADDINGS : this.delta;

        //should do a moving set of things, using move function
        this.horizontalSpeed = new Polar2DVector(this.magnitude * magnitudeSign, 0.0);
        
        movingDir.setStatus(false);
        movingDir.opposite().setStatus(true);
    }
    
    @Override
    public void moveLeft() {
        this.moveBehaviour(Direction.LEFT, LEFT_MAG_SIGN);
    }

    @Override
    public void moveRight() {
        this.moveBehaviour(Direction.RIGHT, RIGHT_MAG_SIGN);
    }

    @Override
    public void stop() {
        
        this.magnitude -= this.magnitude > 0 ? delta : 0.; 
        this.horizontalSpeed = new Polar2DVector(
            this.magnitude * (horizontalSpeed.xComponent() >= 0. ? 1 : -1),
            0.0);
        this.horizontalSpeed = Polar2DVector.nullVector();
    }

    @Override
    public Direction direction() {
        return this.currentDirection;
    }

    //ALTERNATIVE
    // in movebehavius use two bools as parameter, respectly moveRIght and moveLeft
    //will make things simpler, declare them in contructor and as fields
    
}
