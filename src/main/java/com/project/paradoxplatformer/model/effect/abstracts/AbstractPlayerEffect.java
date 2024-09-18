package com.project.paradoxplatformer.model.effect.abstracts;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.model.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

/**
 * AbstractPlayerEffect provides a base implementation for effects that affect a
 * player.
 * It ensures the target is recognized as a player and provides access to the
 * player model for subclasses.
 */
public abstract class AbstractPlayerEffect extends AbstractRecreatableEffect {

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
    protected abstract CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject);

    /**
     * {@inheritDoc}
     */
    @Override
    public RecreateableEffect recreate() {
        return this;
    }

    /**
     * Gets the player involved in the effect, if present.
     *
     * @return An Optional containing the PlayerModel if the target of the effect is
     *         a player, or an empty Optional otherwise.
     */
    public Optional<PlayerModel> getPlayer() {
        return player;
    }
}
