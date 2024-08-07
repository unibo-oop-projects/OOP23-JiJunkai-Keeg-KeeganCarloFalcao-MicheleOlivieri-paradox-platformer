package com.project.paradoxplatformer.utils.collision;

import java.util.List;
import java.util.stream.IntStream;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.effect.EffectHandler;

public class CollisionManager {
    private final EffectHandler effectHandler;

    public CollisionManager(EffectHandler effectHandler) {
        this.effectHandler = effectHandler;
    }

    // Handle collision detection
    public void detectCollisions(List<? extends CollidableGameObject> collidableGameObjects) {
        IntStream.range(0, collidableGameObjects.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, collidableGameObjects.size())
                        .mapToObj(j -> new CollisionPair(collidableGameObjects.get(i), collidableGameObjects.get(j))))
                .filter(pair -> CollisionDetector.isColliding(pair.c1, pair.c2))
                .forEach(pair -> {
                    effectHandler.applyEffects(pair.c1, pair.c2);
                    effectHandler.applyEffects(pair.c2, pair.c1);
                });
    }

    private static class CollisionPair {
        final CollidableGameObject c1;
        final CollidableGameObject c2;

        CollisionPair(CollidableGameObject c1, CollidableGameObject c2) {
            this.c1 = c1;
            this.c2 = c2;
        }
    }
}
