package com.project.paradoxplatformer.model.effect.managers;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

/**
 * Manages effects that are associated with collision types.
 * Each collision type can have a chain of effects applied to it.
 */
public final class TypeEffectsManager {

    // Maps each CollisionType to its associated ChainOfEffects
    private final Map<CollisionType, ChainOfEffects> typeEffectsMap = new EnumMap<>(CollisionType.class);

    /**
     * Adds a single effect to the specified collision type.
     * If the collision type already has effects, the new effect is appended to the
     * existing chain.
     *
     * @param type           the collision type to associate the effect with
     * @param effectSupplier supplier providing the effect to be added
     */
    public void addEffects(final CollisionType type, final Supplier<Effect> effectSupplier) {
        // Merge the new effect into the existing chain, or create a new one if none
        // exists
        typeEffectsMap.merge(type,
                ChainOfEffectsBuilder.builder().addEffect(effectSupplier.get()).build(),
                (existingChain, newChain) -> ChainOfEffectsBuilder.builder()
                        .addEffects(existingChain.getEffects())
                        .addEffects(newChain.getEffects())
                        .build());
    }

    /**
     * Adds a chain of effects to the specified collision type.
     * If the collision type already has effects, the new chain is appended to the
     * existing chain.
     *
     * @param type     the collision type to associate the chain of effects with
     * @param newChain the new chain of effects to be added
     */
    public void addEffects(final CollisionType type, final ChainOfEffects newChain) {
        // Merge the new chain into the existing chain, or create a new one if none
        // exists
        typeEffectsMap.merge(type,
                newChain,
                (existingChain, toAddChain) -> ChainOfEffectsBuilder.builder()
                        .addEffects(existingChain.getEffects())
                        .addEffects(toAddChain.getEffects())
                        .build());
    }

    /**
     * Replaces the existing chain of effects for the specified collision type with
     * a new chain.
     *
     * @param type     the collision type whose effects are to be replaced
     * @param newChain the new chain of effects
     */
    public void replaceEffects(final CollisionType type, final ChainOfEffects newChain) {
        typeEffectsMap.put(type, newChain);
    }

    /**
     * Retrieves the chain of effects associated with the specified collision type.
     * If no effects are associated, returns an empty chain.
     *
     * @param type the collision type to retrieve effects for
     * @return the chain of effects associated with the collision type
     */
    public ChainOfEffects getEffects(final CollisionType type) {
        // Return the existing chain or an empty chain if none exists
        return typeEffectsMap.getOrDefault(type, ChainOfEffectsBuilder.builder().build());
    }

    /**
     * Clears the effects associated with the specified collision type.
     *
     * @param type the collision type whose effects are to be cleared
     */
    public void clearEffects(final CollisionType type) {
        typeEffectsMap.remove(type);
    }
}
