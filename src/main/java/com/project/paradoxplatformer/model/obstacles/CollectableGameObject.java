package com.project.paradoxplatformer.model.obstacles;

import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.player.PlayerModel;

public interface CollectableGameObject extends MutableObject{
    void getCollected(PlayerModel player);
}
