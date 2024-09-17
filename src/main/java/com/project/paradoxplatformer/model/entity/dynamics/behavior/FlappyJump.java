package com.project.paradoxplatformer.model.entity.dynamics.behavior;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;



public class FlappyJump extends AbstractJumpBehavior {
    
    private static final double POWER = 3;
    private static final double ANTI_GRAVITY = -POWER+1;
    private double grav = ANTI_GRAVITY;    

    @Override
    public Optional<Vector2D> jump() {
        
        this.grav = POWER;
        return Optional.of(new Simple2DVector(0., POWER));
    }

    @Override
    public Vector2D fall() {
        return new Simple2DVector(0., grav-=0.25);
    }

    @Override
    public void resetGravity() {
        this.grav = 3;
    }

}
