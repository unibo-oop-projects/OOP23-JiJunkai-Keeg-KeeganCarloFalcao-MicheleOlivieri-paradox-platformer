package com.project.paradoxplatformer.model.player;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;

/**
 * A simple inventory implementation with basic add and remove actions.
 * It serves as a storage for collectable game objects.
 */
public final class SimpleInventory implements Inventory {

    private final Set<CollectableGameObject> items;

    /**
     * A no-argument constructor for initializing the inventory.
     */
    public SimpleInventory() {
        this.items = new LinkedHashSet<>();
    }

    /**
     * A constructor used for immutable operations.
     * 
     * @param defensive A defensive copy to be reinitialized, making the class fully immutable.
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
