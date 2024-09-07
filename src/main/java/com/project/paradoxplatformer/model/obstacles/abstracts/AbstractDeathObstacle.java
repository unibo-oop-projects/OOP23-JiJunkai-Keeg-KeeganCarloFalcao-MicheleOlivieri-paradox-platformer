package com.project.paradoxplatformer.model.obstacles.abstracts;

import java.util.Optional;
import java.util.Queue;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.obstacles.damageableobstacles.DamageableObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.controller.games.GameEventListener;

public abstract class AbstractDeathObstacle extends AbstractObstacle implements DamageableObstacle {

    private static final int DEFAULT_DAMAGE_POINTS = 100;
    protected Optional<Integer> damagePoints; // Modificato in Optional
    private GameEventListener gameEventListener;

    protected AbstractDeathObstacle(final Coord2D position, final Dimension dimension,
            final Optional<Integer> damagePoints) {
        super(position, dimension);
        this.damagePoints = damagePoints;
    }

    protected AbstractDeathObstacle(final Coord2D position, final Dimension dimension) {
        this(position, dimension, Optional.of(DEFAULT_DAMAGE_POINTS));
    }

    public void effect(Optional<ControllableObject> ob) {
        // ob.ifPresent(player -> this.inflictDamage(player));
        
        if (gameEventListener != null) {
            System.out.println("Player death event triggered.");
            gameEventListener.onPlayerDeath(); // Notifica l'evento al controller
        } else {
            System.out.println("No GameEventListener attached.");
        }
    }

    @Override
    public void inflictDamage(ControllableObject player) {
        System.out.println("Inflict damage called on player.");
        // Usa il valore predefinito se damagePoints è vuoto
        //  int damage = damagePoints.orElse(DEFAULT_DAMAGE_POINTS);
        // player.decreaseLifePoints(damage); // Supponendo che ci sia un metodo del
        // genere

        // this.triggerExplosion();
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

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.DEATH_OBS;
    }

}
