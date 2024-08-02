package com.project.paradoxplatformer.utils.collision;

import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

public class CollisionEvent {
    private final CollisionType type;
    private final Collidable target;

    public CollisionEvent(CollisionType type, Collidable target) {
        this.type = type;
        this.target = target;
    }

    public CollisionType getType() {
        return type;
    }

    public Collidable getTarget() {
        return target;
    }
}
