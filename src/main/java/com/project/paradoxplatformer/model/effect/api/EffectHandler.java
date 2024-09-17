package com.project.paradoxplatformer.model.effect.api;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import com.project.paradoxplatformer.model.effect.managers.ChainOfEffects;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

/**
 * Interface for handling effects in the game. Provides methods for adding,
 * applying, and resetting effects based on collision types and specific game
 * objects.
 */
public interface EffectHandler {

    /**
     * Adds a collision effect for a specific collision type.
     *
     * @param type           the collision type
     * @param effectSupplier the supplier for the effect to be added
     */
    void addCollisionEffectsForType(CollisionType type, Supplier<Effect> effectSupplier);

    /**
     * Adds a chain of effects for a specific collision type.
     *
     * @param type     the collision type
     * @param newChain the chain of effects to be added
     */
    void addCollisionEffectsForType(CollisionType type, ChainOfEffects newChain);

    /**
     * Adds a collision effect for a specific collision type and game object.
     *
     * @param type           the collision type
     * @param object         the game object
     * @param effectSupplier the supplier for the effect to be added
     */
    void addCollisionEffectsForObject(CollisionType type, CollidableGameObject object, Supplier<Effect> effectSupplier);

    /**
     * Adds a chain of effects for a specific collision type and game object.
     *
     * @param type     the collision type
     * @param object   the game object
     * @param newChain the chain of effects to be added
     */
    void addCollisionEffectsForObject(CollisionType type, CollidableGameObject object, ChainOfEffects newChain);

    /**
     * Applies effects to the target object based on its collision type and specific
     * instance.
     *
     * @param source the source game object
     * @param target the target game object
     * @return a CompletableFuture that completes when all effects have been applied
     */
    CompletableFuture<Void> applyEffects(CollidableGameObject source, CollidableGameObject target);

    /**
     * Retrieves all effects associated with a specific game object, combining both
     * type and object-specific effects.
     *
     * @param object the game object
     * @return a ChainOfEffects instance containing all combined effects
     */
    ChainOfEffects getAllEffects(CollidableGameObject object);

    /**
     * Resets the effects associated with a specific game object and collision type,
     * recreating effects if possible.
     *
     * @param object the game object
     * @param type   the collision type
     */
    void reset(CollidableGameObject object, CollisionType type);
}
