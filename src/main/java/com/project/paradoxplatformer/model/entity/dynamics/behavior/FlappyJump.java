package com.project.paradoxplatformer.model.entity.dynamics.behavior;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;

public class FlappyJump implements JumpBehavior {
    
    private static final double POWER = 7;
    private static final double ANTI_GRAVITY = -POWER+1;
    private double grav = ANTI_GRAVITY;    

    @Override
    public Optional<Vector2D> jump() {
        
        this.grav = POWER;
        return Optional.of(new Simple2DVector(0., POWER));
    }

    @Override
    public Vector2D fall() {
        return new Simple2DVector(0., grav-=0.5);
    }

}
