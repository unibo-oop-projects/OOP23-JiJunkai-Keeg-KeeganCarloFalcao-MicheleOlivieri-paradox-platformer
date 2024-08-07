package com.project.paradoxplatformer.model.trigger.api;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.entity.GameObject;
import com.project.paradoxplatformer.utils.collision.api.Collidable;
import java.util.Optional;

public interface Trigger extends Collidable {
    void activate(Optional<? extends Collidable> target);
}
