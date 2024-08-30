package com.project.paradoxplatformer.model.entity.dynamics.behavior;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public class PlatformJump implements JumpBehavior {

    private static final double POWER = 13;
    private static final double ANTI_GRAVITY = -POWER+1;
    private double grav = ANTI_GRAVITY;

    @Override
    public Optional<Vector2D> jump() {
        if(grav == ANTI_GRAVITY) {
            grav = POWER;
            return Optional.of(new Simple2DVector(0., POWER));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Vector2D fall() {
        if(grav > ANTI_GRAVITY && grav <= POWER) {
            return new Simple2DVector(0., grav-=1);
        }else {
            return Polar2DVector.nullVector();   
        }
    }

}
