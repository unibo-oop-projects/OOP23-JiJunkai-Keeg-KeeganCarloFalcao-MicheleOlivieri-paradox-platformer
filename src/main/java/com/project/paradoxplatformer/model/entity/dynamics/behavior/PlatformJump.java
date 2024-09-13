package com.project.paradoxplatformer.model.entity.dynamics.behavior;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public class PlatformJump implements JumpBehavior {

    private static final double POWER = 13;
    private static final double ANTI_GRAVITY = -POWER + 1;
    private double grav = ANTI_GRAVITY;
    private boolean isFalling = true;

    @Override
    public Optional<Vector2D> jump() {
        if (grav == ANTI_GRAVITY) {
            // Imposta la gravità al valore di salto e ripristina lo stato di caduta
            grav = POWER;
            isFalling = true;
            return Optional.of(new Simple2DVector(0., POWER));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Vector2D fall() {
        if (isFalling) {
            return new Simple2DVector(0., grav -= 1);
        } else {
            // Se il player non sta cadendo, restituisce un vettore nullo per fermare il movimento verticale
            return new Simple2DVector(0., 0.);
        }
    }

    public void setFalling(boolean falling) {
        this.isFalling = falling;
    }

    public boolean isFalling() {
        return isFalling;
    }

    public void resetGravity() {
        this.grav = ANTI_GRAVITY;
    }
    
}
