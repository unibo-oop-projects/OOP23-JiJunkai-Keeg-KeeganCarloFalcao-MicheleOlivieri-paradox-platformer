package com.project.paradoxplatformer.model.trigger.factory;

import java.util.Optional;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.entity.GameObject;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.AbstractTrigger;
import com.project.paradoxplatformer.utils.collision.api.Collidable;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class Warp extends AbstractTrigger {

    private Coord2D coord;

    public Warp(double destinationX, double destinationY) {
        this.coord = new Coord2D(destinationX, destinationY);
    }

    public Warp(Coord2D coord2d) {
        this.coord = coord2d;
    }

    @Override
    public void activate(Optional<? extends Collidable> target) {
        if (target.isPresent() && target.get() instanceof PlayerModel) {
            PlayerModel player = (PlayerModel) target.get();
            // player.setPosition(coord);
            System.out.println("Player warped to " + coord.x() + ", " + coord.y());
        }
    }
}
