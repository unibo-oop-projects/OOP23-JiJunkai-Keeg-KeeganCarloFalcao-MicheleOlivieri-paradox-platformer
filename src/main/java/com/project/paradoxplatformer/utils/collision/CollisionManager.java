package com.project.paradoxplatformer.utils.collision;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.EventManager;
import com.project.paradoxplatformer.utils.EventType;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.effect.EffectHandler;

public class CollisionManager {

    private EffectHandler effectHandler;

    public CollisionManager(EffectHandler initialEffectHandler) {
        this.effectHandler = initialEffectHandler;
        EventManager.getInstance().subscribe(EventType.EFFECT_HANDLER_UPDATED, this::updateEffectHandler);
    }

    private void updateEffectHandler(EffectHandler newEffectHandler, Object extra) {
        System.out.println("CollisionManger updating its effecthandler.");
        this.effectHandler = newEffectHandler;
    }

    public void handleCollisions(Collection<? extends CollidableGameObject> collidableGameObjects,
            CollidableGameObject player) {
        Set<CollidableGameObject> collidingObjects = detectCollisions(collidableGameObjects, player);

        observeCollisions(collidingObjects, player, effectHandler::applyEffects, effectHandler::reset);
    }

    private Set<CollidableGameObject> detectCollisions(Collection<? extends CollidableGameObject> collidableGameObjects,
            CollidableGameObject player) {
        Set<CollidableGameObject> collidingObjects = new HashSet<>();

        collidableGameObjects.stream()
                .filter(object -> object != player || collidableGameObjects.contains(object))
                .forEach(object -> {
                    if (CollisionDetector.isColliding(player, object)) {
                        collidingObjects.add(object);
                    }
                });

        return collidingObjects;
    }

    private void observeCollisions(Set<CollidableGameObject> collidingObjects,
            CollidableGameObject player,
            BiConsumer<CollidableGameObject, CollidableGameObject> onCollideStart,
            BiConsumer<CollidableGameObject, CollisionType> onCollideEnd) {

        collidingObjects.forEach(object -> onCollideStart.accept(player, object));
        collidingObjects.forEach(object -> onCollideEnd.accept(object, object.getCollisionType()));
    }
}
