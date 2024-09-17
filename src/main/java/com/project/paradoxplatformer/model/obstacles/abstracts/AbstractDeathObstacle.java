package com.project.paradoxplatformer.model.obstacles.abstracts;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

import com.project.paradoxplatformer.controller.games.GameEventListener;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.obstacles.damageableobstacles.DamageableObstacle;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public abstract class AbstractDeathObstacle extends AbstractObstacle implements DamageableObstacle {

    private static final int DEFAULT_DAMAGE_POINTS = 100;
    protected Optional<Integer> damagePoints; // Modificato in Optional
    private GameEventListener gameEventListener;

    protected AbstractDeathObstacle(final int key, final Coord2D position, final Dimension dimension,
            final Optional<Integer> damagePoints, final Queue<TrajectoryInfo> trajectoryQueue) {
        super(key, position, dimension, trajectoryQueue);
        this.damagePoints = damagePoints;
    }

    protected AbstractDeathObstacle(final int key, final Coord2D position, final Dimension dimension, final Queue<TrajectoryInfo> trajectoryQueue) {
        this(key, position, dimension, Optional.of(DEFAULT_DAMAGE_POINTS), trajectoryQueue);
    }

    public void onCollision(Optional<ControllableObject> ob) {
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
        int damage = damagePoints.orElse(DEFAULT_DAMAGE_POINTS);
        // player.decreaseLifePoints(damage); 

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

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.DEATH_OBS;
    }

}
