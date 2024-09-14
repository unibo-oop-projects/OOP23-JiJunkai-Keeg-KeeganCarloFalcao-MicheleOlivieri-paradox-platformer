package com.project.paradoxplatformer.model.effect;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.api.OneTimeEffect;
import com.project.paradoxplatformer.utils.EventManager;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

/**
 * Abstract base class for one-time effects. These effects are applied once
 * and automatically cleaned up (removed) after application.
 */
public abstract class AbstractOneTimeEffect extends AbstractEffect implements OneTimeEffect {

    /**
     * Applies the effect to both target and self, then runs cleanup afterward.
     *
     * @param target the optional target object
     * @param self   the optional self object
     * @return a CompletableFuture representing the completion of applying the
     *         effect
     */
    @Override
    public CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target,
            Optional<? extends CollidableGameObject> self) {
        // Apply the effect, then trigger cleanup once done
        return super.apply(target, self).thenRun(() -> this.cleanup(self));
    }

    /**
     * Cleanup logic for removing the effect once it's applied.
     * 
     * @param self the optional self object
     */
    protected void cleanup(Optional<? extends CollidableGameObject> self) {
        System.out.println("One time effect is in clean up mode.");
        // Publish an event to remove the object after the effect is applied
        EventManager.getInstance().publish(ViewEventType.REMOVE_OBJECT, PageIdentifier.EMPTY, self);
    }
}
