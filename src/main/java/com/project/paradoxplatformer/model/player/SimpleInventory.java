package com.project.paradoxplatformer.model.player;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;

public class SimpleInventory implements Inventory {

    private final Set<CollectableGameObject> items;

    public SimpleInventory() {
        this.items = new LinkedHashSet<>();
    }

    public SimpleInventory(Set<CollectableGameObject> defCopy) {
        this.items = new LinkedHashSet<>(defCopy);
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
                .collect(
                    Collectors.groupingBy(
                        CollectableGameObject::getName,
                        Collectors.counting()
                    )
                )
            );
    }

    @Override
    public Set<CollectableGameObject> getImmutableItems() {
        return Collections.unmodifiableSet(this.items);
    }
    
}
