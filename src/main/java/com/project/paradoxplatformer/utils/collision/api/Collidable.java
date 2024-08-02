package com.project.paradoxplatformer.utils.collision.api;

public interface Collidable {
    CollisionType getCollisionType();

    boolean checkCollision(Collidable other);
}