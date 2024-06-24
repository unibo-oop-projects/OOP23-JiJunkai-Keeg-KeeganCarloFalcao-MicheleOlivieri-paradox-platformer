package com.project.paradoxplatformer.model.entity.dynamics.abstracts;

import com.project.paradoxplatformer.model.entity.dynamics.HorizontalObject;
import com.project.paradoxplatformer.utils.geometries.modifiers.MovingType;
import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;


public abstract class AbstractHorizontalObject implements HorizontalObject {

    //Conventional cartesian values
    private static final int LEFT_MAG_SIGN = -1;
    private static final int RIGHT_MAG_SIGN = 1;
    private static final double RESET_MAG = 0.;
    private static final double NO_ADDINGS = 0.;

    protected double magnitude;
    private final double limit;
    private final double delta;
    protected Vector2D horizontalSpeed;


    protected AbstractHorizontalObject(final double limit, final double delta) {
        this.magnitude = RESET_MAG;
        this.delta = delta;
        this.limit = limit;
    }

    private void moveBehaviour(MovingType movingDir, double magnitudeSign) {
        if(movingDir.getStatus()) {
            this.stop();
        }
        this.magnitude += this.magnitude > this.limit ? NO_ADDINGS : this.delta;
        //should do a moving set of things, using move function
        this.horizontalSpeed = new Polar2DVector(this.magnitude * magnitudeSign, 0.0);
        
        movingDir.setStatus(false);
        movingDir.opposite().setStatus(true);
    }
    
    @Override
    public void moveLeft() {
        moveBehaviour(MovingType.LEFT, LEFT_MAG_SIGN);
    }

    @Override
    public void moveRight() {
        moveBehaviour(MovingType.RIGHT, RIGHT_MAG_SIGN);
    }

    @Override
    public void stop() {
        this.magnitude = RESET_MAG;
        this.horizontalSpeed = Polar2DVector.nullVector();
    }

    //ALTERNATIVE
    // in movebehavius use two bools as parameter, respectly moveRIght and moveLeft
    //will make things simpler, declare them in contructor and as fields
    
}
