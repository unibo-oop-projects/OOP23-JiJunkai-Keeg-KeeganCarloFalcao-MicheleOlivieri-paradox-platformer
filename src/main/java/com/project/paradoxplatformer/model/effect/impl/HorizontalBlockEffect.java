package com.project.paradoxplatformer.model.effect.impl;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.abstracts.AbstractRecreatableEffect;
import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.model.effect.api.RecreateableEffect;
import com.project.paradoxplatformer.model.obstacles.Wall;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.physic.Direction;

/**
 * Effects to handle stopping/blocking effect of the target (in this case is
 * only the player) to a Wall (it is specific for walls).
 */
public final class HorizontalBlockEffect extends AbstractRecreatableEffect {

    private static final double TOLERANCE = 1.d;

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
                    .filter(Wall.class::isInstance)
                    .filter(g -> this.player.isPresent())
                    .map(Wall.class::cast)
                    .ifPresent(w -> {
                        if (player.get().getPosition().y() >= w.getPosition().y() + w.getDimension().height()) {
                            this.player.get().setDisplacement(
                                    new Coord2D(
                                            this.player.get().getPosition().x(),
                                            w.getPosition().y() + w.getDimension().height()));
                        } else {
                            if (player.get().direction() == Direction.LEFT) {
                                this.player.get().setDisplacement(
                                        w.getPosition().x() + w.getDimension().width() + TOLERANCE);
                            } else if (player.get().direction() == Direction.RIGHT) {
                                this.player.get().setDisplacement(
                                        w.getPosition().x() - this.player.get().getDimension().width() - TOLERANCE);
                            }
                        }
                        System.out.println(this.player.get().toString());
                        this.player.get().stopFall();
                    });
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecreateableEffect recreate() {
        return this;
    }
}
