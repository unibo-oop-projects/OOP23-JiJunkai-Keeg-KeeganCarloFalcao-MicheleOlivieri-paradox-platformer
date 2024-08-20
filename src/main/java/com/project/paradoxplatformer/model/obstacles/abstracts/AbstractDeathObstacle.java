package com.project.paradoxplatformer.model.obstacles.abstracts;

import java.util.Optional;
import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.obstacles.DamageableObstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.controller.games.GameEventListener;

public abstract class AbstractDeathObstacle extends AbstractObstacle implements DamageableObstacle {

    private final static int lifePoints = 100;
    protected int damagePoints; //TODO: improve with optional
    private GameEventListener gameEventListener;

    protected AbstractDeathObstacle(final Coord2D position, final Dimension dimension, final int damagePoints) {
        super(position, dimension);
        this.damagePoints = damagePoints;
    }

    protected AbstractDeathObstacle(final Coord2D position, final Dimension dimension) {
        super(position, dimension);
        this.damagePoints = lifePoints;
    }

    public void effect(Optional<ControllableObject> ob) {
        ob.ifPresent(player -> this.inflictDamage(player));
        System.out.println("effect");
    }

    @Override
    public void inflictDamage(ControllableObject player) {
        System.out.println("Inflict damage called on player.");
        //if damagePoint: player.decreaseLifePoints(damagePoints);
        this.triggerExplosion();
        if (gameEventListener != null) {
            System.out.println("Player death event triggered.");
            gameEventListener.onPlayerDeath(); // Notifica l'evento al controller
        } else {
            System.out.println("No GameEventListener attached.");
        }
    }

    protected abstract void triggerExplosion();
    

    @Override
    public void updateState(final long dt) {
        super.updateState(dt);
    }

    public void setGameEventListener(GameEventListener listener) {
        this.gameEventListener = listener;
        System.out.println("GameEventListener set: " + (listener != null));
    }

}
