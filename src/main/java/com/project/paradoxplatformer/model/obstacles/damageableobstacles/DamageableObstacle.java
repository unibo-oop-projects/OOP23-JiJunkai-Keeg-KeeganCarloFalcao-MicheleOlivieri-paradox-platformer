package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;

public interface DamageableObstacle {

    void inflictDamage(ControllableObject player);
}
