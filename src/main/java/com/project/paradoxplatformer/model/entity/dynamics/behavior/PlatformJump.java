package com.project.paradoxplatformer.model.entity.dynamics.behavior;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;

public class PlatformJump implements JumpBehavior {

    private static final double POWER = 13;
    private static final double ANTI_GRAVITY = -POWER+1;
    private double grav = ANTI_GRAVITY;

    @Override
    public Optional<Simple2DVector> jump() {
        if(grav == ANTI_GRAVITY) {
            grav = POWER;
            System.out.println("platforJump");
            return Optional.of(new Simple2DVector(0., POWER));
        } else {
            return Optional.empty();
        }
    }

}
