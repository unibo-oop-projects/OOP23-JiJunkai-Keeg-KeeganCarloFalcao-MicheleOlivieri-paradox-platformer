package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;

import java.util.Optional;
import java.util.Queue;

public final class Warp extends AbstractObstacle {

    private final Coord2D destination; // La destinazione del teletrasporto

    /**
     * da migliorare con possibilità di costruire l'oggeto con un parametro in più 
     * che è la destinazione
     * @param key the unique key
     * @param position of the warp
     * @param dimension of the warp
     * @param destination of the player effects tranfer
     * @param trajectoryQueue of the warp
     */
    
    public Warp(
        final int key,
        final Coord2D position,
        final Dimension dimension,
        final Coord2D destination, 
        final Queue<TrajectoryInfo> trajectoryQueue
    ) {
        super(key, position, dimension, trajectoryQueue);
        this.destination = destination;
    }

    /**
     * {@inheritDoc}
     */
    public void effect(final Optional<ControllableObject> ob) {
        if (ob.isPresent() && ob.get() instanceof PlayerModel) {
            // Teletrasporta il giocatore alla destinazione
            PlayerModel player = (PlayerModel) ob.get();
            player.setPosition(destination);
            System.out.println("Player teleported to " + destination);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CollisionType getCollisionType() {
        return CollisionType.WARP;
    }
}
