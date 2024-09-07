package com.project.paradoxplatformer.utils.collision.api;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;

/**
 * Functional interface for handling collision actions.
 * This is used to apply effects before and after a collision occurs.
 *
 * @param obj  The colliding object.
 * @param type The type of collision.
 */
@FunctionalInterface
public interface CollisionAction {
    void handle(CollidableGameObject obj, CollisionType type);
}
