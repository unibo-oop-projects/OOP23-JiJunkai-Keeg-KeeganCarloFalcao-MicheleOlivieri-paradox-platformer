package com.project.paradoxplatformer.utils.collision;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.effect.EffectHandler;

public class CollisionManager {

    private final EffectHandler effectHandler;

    public CollisionManager(EffectHandler effectHandler) {
        this.effectHandler = effectHandler;
    }

    // Handle collision detection and actions via method references
    public void handleCollisions(Collection<? extends CollidableGameObject> collidableGameObjects,
            CollidableGameObject player) {
        Set<CollidableGameObject> collidingObjects = detectCollisions(collidableGameObjects, player);

        // Observing collisions using method references
        observeCollisions(collidingObjects, player, effectHandler::applyEffects, effectHandler::reset);
    }

    // Collision detection logic
    private Set<CollidableGameObject> detectCollisions(Collection<? extends CollidableGameObject> collidableGameObjects,
            CollidableGameObject player) {
        Set<CollidableGameObject> collidingObjects = new HashSet<>();

        collidableGameObjects.stream()
                .filter(object -> object != player)
                .forEach(object -> {
                    if (CollisionDetector.isColliding(player, object)) {
                        collidingObjects.add(object);
                    }
                });

        return collidingObjects;
    }

    // Observing collisions and applying actions via method references
    private void observeCollisions(Set<CollidableGameObject> collidingObjects,
            CollidableGameObject player,
            BiConsumer<CollidableGameObject, CollidableGameObject> onCollideStart,
            BiConsumer<CollidableGameObject, CollisionType> onCollideEnd) {

        collidingObjects.forEach(object -> onCollideStart.accept(player, object));

        // For each colliding object, apply the end collision action
        collidingObjects.forEach(object -> {
            onCollideEnd.accept(object, object.getCollisionType());
        });
    }
}
