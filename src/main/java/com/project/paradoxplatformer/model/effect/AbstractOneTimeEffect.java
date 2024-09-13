package com.project.paradoxplatformer.model.effect;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.api.OneTimeEffect;
import com.project.paradoxplatformer.utils.EventManager;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

/**
 * Abstract implementation of a one-time effect that is applied and then
 * removed.
 */
public abstract class AbstractOneTimeEffect extends AbstractEffect implements OneTimeEffect {

    @Override
    public CompletableFuture<Void> apply(Optional<? extends CollidableGameObject> target,
            Optional<? extends CollidableGameObject> self) {
        return super.apply(target, self).thenRun(() -> this.cleanup(self));
    }

    /**
     * Cleanup method to remove the effect after it has been applied.
     *
     * @param self the optional self object
     */
    protected void cleanup(Optional<? extends CollidableGameObject> self) {
        System.out.println("One time effect is in clean up mode.");
        EventManager.getInstance().publish(ViewEventType.REMOVE_OBJECT, PageIdentifier.EMPTY, self);
    };
}
