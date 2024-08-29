package com.project.paradoxplatformer.model.entity.dynamics.behavior;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;

public interface JumpBehavior {
    
    Optional<Simple2DVector> jump();
}
