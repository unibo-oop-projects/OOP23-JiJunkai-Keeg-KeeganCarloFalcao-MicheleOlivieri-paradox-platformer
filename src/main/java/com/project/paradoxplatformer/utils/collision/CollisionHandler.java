package com.project.paradoxplatformer.utils.collision;

import java.util.List;
import java.util.Map;

import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import java.util.Optional;

public class CollisionHandler {
    private final Map<CollisionType, Map<Collidable, List<ChainOfEffects>>> collisionEffectsMap;

    public CollisionHandler(Map<CollisionType, Map<Collidable, List<ChainOfEffects>>> collisionEffectsMap) {
        this.collisionEffectsMap = collisionEffectsMap;
    }

    // Handle collision by applying the effects associated with the collidable
    public void handleCollision(Collidable collidable) {
        Map<Collidable, List<ChainOfEffects>> collidableMap = collisionEffectsMap.get(collidable.getCollisionType());
        if (collidableMap != null) {
            List<ChainOfEffects> chainsOfEffects = collidableMap.getOrDefault(collidable, List.of());
            chainsOfEffects.forEach(chain -> chain.applyEffectsSequentially(Optional.of(collidable))
                    .thenRun(() -> System.out.println("Effects applied for collision with " + collidable)));
        }
    }
}
