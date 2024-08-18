package com.project.paradoxplatformer.model.player;

import com.project.paradoxplatformer.model.obstacles.CollectableGameObject;

public interface Inventory {


    void addItem(CollectableGameObject item);

    void removeItem(CollectableGameObject item);
}
