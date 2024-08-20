package com.project.paradoxplatformer.utils.collision;

import java.util.Collection;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.effect.EffectHandler;

public class CollisionManager {
    private final EffectHandler effectHandler;

    public CollisionManager(EffectHandler effectHandler) {
        this.effectHandler = effectHandler;
    }

    // Handle collision detection
    public void detectCollisions(Collection<? extends CollidableGameObject> collidableGameObjects,
            CollidableGameObject player) {
        collidableGameObjects.stream()
                .filter(object -> object != player)
                .forEach(object -> {
                    if (CollisionDetector.isColliding(player, object)) {
                        System.out.println("HEllo");
                        effectHandler.applyEffects(player, object);
                        effectHandler.applyEffects(object, player);
                    }
                });
    }
}
