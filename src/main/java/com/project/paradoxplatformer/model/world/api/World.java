package com.project.paradoxplatformer.model.world.api;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.utils.collision.CollisionManager;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import java.util.Collection;

public interface World {

    Collection<Obstacle> obstacles();

    Collection<Trigger> triggers();

    Collection<MutableObject> objects();

    boolean removeGameObjcts(MutableObject collidableGameObj);

    PlayerModel player();

    CollisionManager getCollisionManager();

    Dimension bounds();
}
