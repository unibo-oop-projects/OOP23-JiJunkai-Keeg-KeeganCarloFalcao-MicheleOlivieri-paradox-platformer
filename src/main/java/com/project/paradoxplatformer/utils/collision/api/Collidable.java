package com.project.paradoxplatformer.utils.collision.api;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;

public interface Collidable {
    CollisionType getCollisionType();

    boolean checkCollision(Collidable other);
}