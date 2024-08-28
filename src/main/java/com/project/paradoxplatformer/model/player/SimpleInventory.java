package com.project.paradoxplatformer.model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;

public class SimpleInventory implements Inventory {

    private List<CollectableGameObject> items;

    public SimpleInventory() {
        this.items = new ArrayList<>();
    }

    @Override
    public void addItem(CollectableGameObject item) {
        this.items.add(item);
    }

    @Override
    public void removeItem(CollectableGameObject item) {
        this.items.remove(item);
    }

    @Override
    public Map<String, Long> getItemsCounts() {
        return Collections.unmodifiableMap(
            this.items.stream()
                .map(CollectableGameObject::getClass)
                .collect(
                    Collectors.groupingBy(
                        Class::getSimpleName, 
                        Collectors.counting()
                    )
                )
            );
    }
    
}
