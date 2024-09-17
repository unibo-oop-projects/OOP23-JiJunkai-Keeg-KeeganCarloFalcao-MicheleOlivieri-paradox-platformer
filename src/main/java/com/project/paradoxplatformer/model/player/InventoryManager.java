package com.project.paradoxplatformer.model.player;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;

public interface InventoryManager {

    Inventory getInventory();

    void collectItem(CollectableGameObject item);

    long getCollectedCoins();
}
