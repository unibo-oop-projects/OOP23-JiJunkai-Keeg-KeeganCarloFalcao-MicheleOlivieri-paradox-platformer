package com.project.paradoxplatformer.utils.collision;

import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.collision.api.CollisionBehavior;

public class CollisionDetector {

    private CollisionBehavior collisionBehavior;

    public void setCollisionBehavior(CollisionBehavior collisionBehavior) {
        this.collisionBehavior = collisionBehavior;
    }

    public void checkAndResolveCollision(Collidable object1, Collidable object2) {
        if (checkCollision(object1, object2)) {
            collisionBehavior.handleCollision(object1, object2);
        }
    }

    private boolean checkCollision(Collidable object1, Collidable object2) {
        // TODO
        return false;
    }
}
