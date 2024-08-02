package com.project.paradoxplatformer.utils.collision;

import java.util.EnumMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Supplier;

import com.project.paradoxplatformer.model.trigger.api.Effect;
import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

public class CollisionManager {
    private final Map<CollisionType, Map<Collidable, List<ChainOfEffects>>> collisionEffectsMap = new EnumMap<>(
            CollisionType.class);

    public void addCollisionEffects(CollisionType type, Collidable collidable, List<Supplier<Effect>> effectSuppliers) {
        ChainOfEffects.Builder builder = ChainOfEffects.builder();
        effectSuppliers.forEach(supplier -> builder.addEffect(supplier.get()));
        ChainOfEffects chain = builder.build();
        collisionEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                .computeIfAbsent(collidable, k -> new ArrayList<>()).add(chain);
    }

    public CollisionHandler createCollisionHandler() {
        return new CollisionHandler(collisionEffectsMap);
    }
}