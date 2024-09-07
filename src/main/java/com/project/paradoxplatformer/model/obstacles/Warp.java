package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.model.obstacles.abstracts.AbstractObstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;

import java.util.Optional;

public class Warp extends AbstractObstacle {

    private final Coord2D destination; // La destinazione del teletrasporto

    public Warp(Coord2D position, Dimension dimension, Coord2D destination) {
        super(position, dimension);
        this.destination = destination;
    }

    public void effect(Optional<ControllableObject> ob) {

        if (ob.isPresent() && ob.get() instanceof PlayerModel) {
            // Teletrasporta il giocatore alla destinazione
            PlayerModel player = (PlayerModel) ob.get();
            player.setPosition(destination);
            System.out.println("Player teleported to " + destination);
        }
        
    }
}
