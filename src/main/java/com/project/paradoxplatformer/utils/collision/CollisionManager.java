package com.project.paradoxplatformer.utils.collision;

import java.util.Collection;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.effect.EffectHandler;
import java.util.HashSet;
import java.util.Set;

public class CollisionManager {
    private final EffectHandler effectHandler;

    public CollisionManager(EffectHandler effectHandler) {
        this.effectHandler = effectHandler;
    }

    // Handle collision detection and return the set of colliding objects
    public Set<CollidableGameObject> detectCollisions(Collection<? extends CollidableGameObject> collidableGameObjects,
            CollidableGameObject player) {
        Set<CollidableGameObject> collidingObjects = new HashSet<>();

        collidableGameObjects.stream()
                .filter(object -> object != player)
                .forEach(object -> {
                    if (CollisionDetector.isColliding(player, object)) {
                        effectHandler.applyEffects(player, object);
                        collidingObjects.add(object);
                    }
                });

        return collidingObjects;
    }
}
