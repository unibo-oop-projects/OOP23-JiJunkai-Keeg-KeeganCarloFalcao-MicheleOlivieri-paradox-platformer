package com.project.paradoxplatformer.utils.collision;

import java.util.Optional;
import java.util.Set;

import com.project.paradoxplatformer.model.effect.EffectHandler;
import com.project.paradoxplatformer.model.effect.GameEventType;
import com.project.paradoxplatformer.model.trigger.Trigger;
import com.project.paradoxplatformer.utils.BiConsumerWithAndThen;
import com.project.paradoxplatformer.utils.EventManager;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

/**
 * Observes collisions between game objects and triggers effects when
 * appropriate.
 * This class monitors collidable objects, applies effects during collisions,
 * and resets them when the collisions end.
 */
public class CollisionObserver {

    private final EffectHandler effectHandler;

    /**
     * Constructs a CollisionObserver with a provided EffectHandler to manage
     * collision effects.
     *
     * @param effectHandler the effect handler responsible for applying and
     *                      resetting effects.
     */
    public CollisionObserver(final EffectHandler effectHandler) {
        this.effectHandler = effectHandler;
    }

    /**
     * Monitors collisions between the player and a set of colliding game objects,
     * applying effects
     * and handling triggers when collisions start or end.
     *
     * @param collidingObjects the set of game objects colliding with the player.
     * @param player           the player game object involved in collisions.
     */
    public void observeCollisions(final Set<CollidableGameObject> collidingObjects, final CollidableGameObject player) {

        // Define actions for when a collision starts and ends
        BiConsumerWithAndThen<CollidableGameObject, CollidableGameObject> onCollideStart = createCollisionTriggerHandlerStart();
        BiConsumerWithAndThen<CollidableGameObject, CollisionType> onCollideEnd = createCollisionTriggerHandlerEnd();

        // Apply effects for every collision between the player and other objects
        collidingObjects.forEach(obj -> onCollideStart.andThen(effectHandler::applyEffects).accept(player, obj));

        // Reset effects when collisions end
        collidingObjects.forEach(obj -> onCollideEnd.andThen(effectHandler::reset).accept(obj, obj.getCollisionType()));
    }

    /**
     * Creates a BiConsumerWithAndThen that handles activation of triggers when
     * collisions start.
     * If either of the objects involved in the collision is a Trigger, it will
     * activate the trigger.
     *
     * @return a BiConsumerWithAndThen for handling collision start and trigger
     *         activation.
     */
    private BiConsumerWithAndThen<CollidableGameObject, CollidableGameObject> createCollisionTriggerHandlerStart() {
        return (object, target) -> {
            activateTriggerIfPresent(object);
            activateTriggerIfPresent(target);
        };
    }

    /**
     * Creates a BiConsumerWithAndThen that handles the removal of triggers when
     * collisions end.
     * If the object is a Trigger, it will trigger the removal process.
     *
     * @return a BiConsumerWithAndThen for handling collision end and trigger
     *         removal.
     */
    private BiConsumerWithAndThen<CollidableGameObject, CollisionType> createCollisionTriggerHandlerEnd() {
        return (object, collisionType) -> {
            removeTriggerIfPresent(object, collisionType);
        };
    }

    /**
     * Activates the trigger if the provided object is an instance of Trigger.
     *
     * @param object the collidable game object that may be a trigger.
     */
    private void activateTriggerIfPresent(final CollidableGameObject object) {
        if (object instanceof Trigger) {
            System.out.println(object + " is triggered from Collision Observer.");
            ((Trigger) object).activate();
        }
    }

    /**
     * Removes the trigger effect if the provided object is a Trigger and matches
     * the specified collision type.
     *
     * @param object        the game object that might need to be removed.
     * @param collisionType the collision type associated with the object.
     */
    private void removeTriggerIfPresent(final CollidableGameObject object, final CollisionType collisionType) {
        if (object instanceof Trigger) {
            System.out.println(object + " is removed from Collision Observer.");
            EventManager.getInstance().publish(GameEventType.REMOVE_OBJECT, PageIdentifier.EMPTY, Optional.of(object));
        }
    }
}
