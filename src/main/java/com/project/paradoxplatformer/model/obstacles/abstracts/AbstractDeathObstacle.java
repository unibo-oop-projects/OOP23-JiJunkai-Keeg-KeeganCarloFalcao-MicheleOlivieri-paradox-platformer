package com.project.paradoxplatformer.model.obstacles.abstracts;

import java.util.Optional;
import java.util.Queue;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.obstacles.DamageableObstacle;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public abstract class AbstractDeathObstacle extends AbstractObstacle implements DamageableObstacle {

    protected int damagePoints;

    protected AbstractDeathObstacle(final Coord2D position, final Dimension dimension, final Queue<TrajectoryInfo> trajStats, final int damagePoints) {
        super(position, dimension, trajStats);
        this.damagePoints = damagePoints;
    }

    @Override
    public void effect(Optional<ControllableObject> ob) {
        ob.ifPresent(player -> inflictDamage(player));
    }

    @Override
    public void inflictDamage(ControllableObject player) {
        //player.decreaseLifePoints(damagePoints);
        this.triggerExplosion();
    }

    protected abstract void triggerExplosion();
}
