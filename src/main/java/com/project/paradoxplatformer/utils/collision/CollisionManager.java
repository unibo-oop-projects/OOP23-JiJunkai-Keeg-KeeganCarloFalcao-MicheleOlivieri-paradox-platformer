package com.project.paradoxplatformer.utils.collision;

import java.util.List;
import java.util.stream.IntStream;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.effect.EffectHandler;

public class CollisionManager {
    private final EffectHandler effectHandler;

    public CollisionManager(EffectHandler effectHandler) {
        this.effectHandler = effectHandler;
    }

    // Handle collision detection
    public void detectCollisions(List<? extends Collidable> collidables) {
        IntStream.range(0, collidables.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, collidables.size())
                        .mapToObj(j -> new CollisionPair(collidables.get(i), collidables.get(j))))
                .filter(pair -> pair.c1.checkCollision(pair.c2))
                .forEach(pair -> {
                    effectHandler.applyEffects(pair.c1, pair.c2);
                    effectHandler.applyEffects(pair.c2, pair.c1);
                });
    }

    private static class CollisionPair {
        final Collidable c1;
        final Collidable c2;

        CollisionPair(Collidable c1, Collidable c2) {
            this.c1 = c1;
            this.c2 = c2;
        }
    }
}
