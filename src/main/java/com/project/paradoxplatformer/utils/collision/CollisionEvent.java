package com.project.paradoxplatformer.utils.collision;

import com.project.paradoxplatformer.utils.collision.api.Collidable;

public class CollisionEvent {
    private final Collidable collider;
    private final Collidable collidedWith;

    public CollisionEvent(Collidable collider, Collidable collidedWith) {
        this.collider = collider;
        this.collidedWith = collidedWith;
    }

    public Collidable getCollider() {
        return collider;
    }

    public Collidable getCollidedWith() {
        return collidedWith;
    }
}