package com.project.paradoxplatformer.model.effect.managers;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

/**
 * Manages effects that are associated with specific game objects and their
 * collision types.
 * Each combination of a collision type and game object can have its own chain
 * of effects.
 */
public final class ObjectEffectsManager {

    // Maps each CollisionType to a map of CollidableGameObject -> ChainOfEffects
    private final Map<CollisionType, Map<CollidableGameObject, ChainOfEffects>> objectEffectsMap = new EnumMap<>(
            CollisionType.class);

    /**
     * Adds a single effect to the specified collision type and game object.
     * If the game object already has effects for the given collision type, the new
     * effect is appended to the existing chain.
     *
     * @param type           the collision type
     * @param object         the game object
     * @param effectSupplier the supplier providing the effect to be added
     */
    public void addEffects(final CollisionType type, final CollidableGameObject object,
            final Supplier<Effect> effectSupplier) {
        // Add or merge effects for the object and collision type
        objectEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                .merge(object,
                        ChainOfEffectsBuilder.builder().addEffect(effectSupplier.get()).build(),
                        this::mergeChains);
    }

    /**
     * Adds a chain of effects to the specified collision type and game object.
     * If the game object already has effects for the given collision type, the new
     * chain is appended to the existing chain.
     *
     * @param type     the collision type
     * @param object   the game object
     * @param newChain the chain of effects to be added
     */
    public void addEffects(final CollisionType type, final CollidableGameObject object, final ChainOfEffects newChain) {
        // Add or merge chains of effects for the object and collision type
        objectEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                .merge(object, newChain, this::mergeChains);
    }

    /**
     * Replaces the current effects for the specified collision type and game object
     * with a new chain of effects.
     *
     * @param type     the collision type
     * @param object   the game object
     * @param newChain the new chain of effects to replace the existing one
     */
    public void replaceEffects(final CollisionType type, final CollidableGameObject object,
            final ChainOfEffects newChain) {
        objectEffectsMap.computeIfAbsent(type, k -> new HashMap<>())
                .put(object, newChain);
    }

    /**
     * Retrieves the chain of effects associated with the specified collision type
     * and game object.
     * If no effects are associated, returns an empty chain.
     *
     * @param type   the collision type
     * @param object the game object
     * @return the chain of effects associated with the collision type and game
     *         object
     */
    public ChainOfEffects getEffects(final CollisionType type, final CollidableGameObject object) {
        // Return the chain of effects for the object and type, or an empty chain if
        // none exists
        return objectEffectsMap.getOrDefault(type, new HashMap<>()).getOrDefault(object,
                ChainOfEffectsBuilder.builder().build());
    }

    /**
     * Clears the effects associated with the specified collision type and game
     * object.
     * If the collision type no longer has any associated effects, it is removed
     * from the map.
     *
     * @param type   the collision type
     * @param object the game object
     */
    public void clearEffects(final CollisionType type, final CollidableGameObject object) {
        // Remove the effects for the specific object and type
        final Map<CollidableGameObject, ChainOfEffects> objectMap = objectEffectsMap.get(type);
        if (objectMap != null) {
            objectMap.remove(object);
            if (objectMap.isEmpty()) {
                objectEffectsMap.remove(type);
            }
        }
    }

    /**
     * Merges two chains of effects into one, combining their individual effects.
     *
     * @param existingChain the existing chain of effects
     * @param newChain      the new chain of effects to merge
     * @return the merged chain of effects
     */
    private ChainOfEffects mergeChains(final ChainOfEffects existingChain, final ChainOfEffects newChain) {
        return ChainOfEffectsBuilder.builder()
                .addEffects(existingChain.getEffects())
                .addEffects(newChain.getEffects())
                .build();
    }
}
