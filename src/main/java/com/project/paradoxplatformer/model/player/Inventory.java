package com.project.paradoxplatformer.model.player;

import java.util.Map;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;

public interface Inventory {

    void addItem(CollectableGameObject item);

    void removeItem(CollectableGameObject item);

    Map<String, Long> getItemsCounts();
}
