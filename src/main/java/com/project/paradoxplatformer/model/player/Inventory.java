package com.project.paradoxplatformer.model.player;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;

/**
 * Represents an inventory which a player can equip.
 * <p>
 * It provides basic actions such as add and remve an time, furthermore it has a
 * special method to group the items based on their counting
 * </p>
 */
public interface Inventory {

    /**
     * adds a collectable game objects to the inventory collection.
     * 
     * @param item to be added
     */
    void addItem(CollectableGameObject item);

    /**
     * removes a collectable game objects to the inventory collection.
     * 
     * @param item to be removed
     */
    void removeItem(CollectableGameObject item);

    /**
     * Provides the access to get the items collection (Note that they are
     * unmodifiable).
     * 
     * @return the immutable items collections
     */
    Set<CollectableGameObject> getImmutableItems();

    /**
     * A special function to collect game objects and group them based on the object
     * name and outputing their enumeration.
     * 
     * @return the grouped map
     */
    default Map<String, Long> getItemsCounts() {
        return Collections.unmodifiableMap(
                this.getImmutableItems().stream()
                        .collect(
                                Collectors.groupingBy(
                                        CollectableGameObject::getName,
                                        Collectors.counting())));
    }
}
