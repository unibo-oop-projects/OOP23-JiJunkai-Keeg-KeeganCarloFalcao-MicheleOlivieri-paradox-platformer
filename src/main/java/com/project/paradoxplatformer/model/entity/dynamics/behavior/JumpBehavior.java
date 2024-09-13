package com.project.paradoxplatformer.model.entity.dynamics.behavior;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.vector.api.Vector2D;

/**
 * Jump base interface, Provides a default method to
 * manage gravity and jump. By strategy pattern could be
 * implemented as platfrom or flappy bird. 
 */
public interface JumpBehavior {

    Optional<Vector2D> jump();

    Vector2D fall();
}
