package com.project.paradoxplatformer.model.entity.dynamics.behavior;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

public interface JumpBehavior {

    Optional<Vector2D> jump();

    Vector2D fall();
}
