package com.project.paradoxplatformer.model.entity.dynamics.behavior;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;

public class FlappyJump implements JumpBehavior {
    
    private static final double POWER = 13;

    @Override
    public Optional<Simple2DVector> jump() {
        
        System.out.println("flappyJump");
        return Optional.of(new Simple2DVector(0., POWER));
    }

}
