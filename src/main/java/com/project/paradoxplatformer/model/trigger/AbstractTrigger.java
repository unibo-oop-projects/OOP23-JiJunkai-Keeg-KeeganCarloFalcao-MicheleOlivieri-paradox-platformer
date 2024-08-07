package com.project.paradoxplatformer.model.trigger;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

import java.util.Optional;

public abstract class AbstractTrigger implements Trigger {

    private final CollisionType type = CollisionType.TRIGGER;

    @Override
    public CollisionType getCollisionType() {
        return type;
    }

    @Override
    public boolean checkCollision(Collidable other) {
        return false; // Placeholder
    }

    @Override
    public abstract void activate(Optional<? extends Collidable> target);
}
