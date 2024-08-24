package com.project.paradoxplatformer.model.obstacles.damageableobstacles;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;

public interface DamageableObstacle {

    void inflictDamage(ControllableObject player);
}
