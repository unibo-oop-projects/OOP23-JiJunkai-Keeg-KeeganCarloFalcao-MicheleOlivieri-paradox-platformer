package com.project.paradoxplatformer.model.effect.impl;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.controller.event.EventManager;
import com.project.paradoxplatformer.controller.event.GameEventType;
import com.project.paradoxplatformer.model.effect.abstracts.AbstractRecreatableEffect;
import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.model.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.model.entity.CollectableGameObject;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

/**
 * Such effects permits the player to collect {@code CollectableGameObject} to update its inventory
 * and erase it from the (Model) world objects.
 * 
 * @see com.project.paradoxplatformer.model.entity.CollectableGameObject
 */
public final class CollectingEffect extends AbstractRecreatableEffect {

    private Optional<PlayerModel> player = Optional.empty();

    /**
     * {@inheritDoc}
     */
    @Override
    protected CompletableFuture<Void> applyToTarget(final Optional<? extends CollidableGameObject> target) {
        return target.map(gameObject -> {
            if (gameObject instanceof PlayerModel pl) {
                this.player = Optional.of(pl);
            }
            return applyToGameObject(gameObject);
        }).orElseGet(Effect::empty);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected CompletableFuture<Void> applyToGameObject(final CollidableGameObject gameObject) {
        return CompletableFuture.runAsync(() -> {
            Optional.of(gameObject)
                    .filter(CollectableGameObject.class::isInstance)
                    .filter(g -> this.player.isPresent())
                    .map(CollectableGameObject.class::cast)
                    .ifPresent(this.player.get()::collectItem);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected CompletableFuture<Void> applyToSelf(final Optional<? extends CollidableGameObject> self) {
        return super.applyToSelf(self)
                .thenAccept(obj -> 
                    EventManager.getInstance()
                        .publish(GameEventType.REMOVE_OBJECT, PageIdentifier.GAME, self)
                );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecreateableEffect recreate() {
        return this;
    }

}
