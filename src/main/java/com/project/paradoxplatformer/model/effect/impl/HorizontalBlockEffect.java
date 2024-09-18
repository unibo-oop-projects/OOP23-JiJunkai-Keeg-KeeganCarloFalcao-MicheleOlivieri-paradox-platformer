package com.project.paradoxplatformer.model.effect.impl;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.effect.abstracts.AbstractPlayerEffect;
import com.project.paradoxplatformer.model.obstacles.Wall;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.physic.Direction;

/**
 * Effects to handle stopping/blocking effect of the target (in this case is
 * only the player) to a Wall (it is specific for walls).
 */
public final class HorizontalBlockEffect extends AbstractPlayerEffect {

    private static final double TOLERANCE = 1.d;

    /**
     * {@inheritDoc}
     */
    @Override
    protected CompletableFuture<Void> applyToGameObject(final CollidableGameObject gameObject) {
        return CompletableFuture.runAsync(() -> {
            Optional.of(gameObject)
                    .filter(Wall.class::isInstance)
                    .filter(g -> getPlayer().isPresent())
                    .map(Wall.class::cast)
                    .ifPresent(w -> {
                        if (getPlayer().get().getPosition().y() >= w.getPosition().y() + w.getDimension().height()) {
                            getPlayer().get().setDisplacement(
                                    new Coord2D(
                                            getPlayer().get().getPosition().x(),
                                            w.getPosition().y() + w.getDimension().height()));
                        } else {
                            if (getPlayer().get().direction() == Direction.LEFT) {
                                getPlayer().get().setDisplacement(
                                        w.getPosition().x() + w.getDimension().width() + TOLERANCE);
                            } else if (getPlayer().get().direction() == Direction.RIGHT) {
                                getPlayer().get().setDisplacement(
                                        w.getPosition().x() - getPlayer().get().getDimension().width() - TOLERANCE);
                            }
                        }
                        // System.out.println(getPlayer().get().toString());
                        getPlayer().get().stopFall();
                    });
        });
    }

}
