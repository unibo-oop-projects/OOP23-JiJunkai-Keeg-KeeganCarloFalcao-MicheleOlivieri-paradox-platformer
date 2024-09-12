package com.project.paradoxplatformer.model.world.api;

import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.trigger.api.Trigger;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import java.util.Collection;

public interface World {

    Collection<Obstacle> obstacles();

    Collection<Trigger> triggers();

    Collection<MutableObject> gameObjects();

    boolean removeGameObjcts(MutableObject mutGameObject);

    PlayerModel player();

    Dimension bounds();
}
