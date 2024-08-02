package com.project.paradoxplatformer.utils.collision.factory;

import com.project.paradoxplatformer.utils.collision.api.CollisionBehavior;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

public class CollisionBehaviorFactory {

    public static CollisionBehavior createCollisionBehavior(CollisionType type) {
        switch (type) {
            case OBSTACLE:
                return new PlayerObstacleCollision();
            case TRIGGER:
                return new PlayerTriggerCollision();
            case BOUNDARY:
                return new PlayerTriggerCollision();
            // Add more cases for other collision types
            default:
                throw new IllegalArgumentException("Unsupported collision behavior type");
        }
    }
}