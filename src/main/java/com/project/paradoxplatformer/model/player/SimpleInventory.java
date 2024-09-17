package com.project.paradoxplatformer.model.player;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;

/**
 * A simple inventory implementation with basic add and remove actions (it a storage)
 */
public final class SimpleInventory implements Inventory {

    private final Set<CollectableGameObject> items;

    /**
     * A non-argument contructor for initializing the inventory
     */
    public SimpleInventory() {
        this.items = new LinkedHashSet<>();
    }

    /**
     * A contructor used for immutable operations
     * @param defensive copy to be reinitialized making a fully immutable class
     */
    public SimpleInventory(final Set<CollectableGameObject> defensive) {
        this.items = new LinkedHashSet<>(defensive);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(final CollectableGameObject item) {
        this.items.add(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeItem(final CollectableGameObject item) {
        this.items.remove(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<CollectableGameObject> getImmutableItems() {
        return Collections.unmodifiableSet(this.items);
    }
    
}
