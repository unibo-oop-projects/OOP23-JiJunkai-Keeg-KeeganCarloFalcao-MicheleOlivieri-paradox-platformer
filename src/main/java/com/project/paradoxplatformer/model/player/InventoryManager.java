package com.project.paradoxplatformer.model.player;

import java.util.HashMap;
import java.util.Map;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;

public class InventoryManager {

    private final Map<String, Long> inventory;

    public InventoryManager() {
        this.inventory = new HashMap<>();
    }

    public void addItem(CollectableGameObject item) {
        String itemName = item.getName();
        this.inventory.put(itemName, this.inventory.getOrDefault(itemName, 0L) + 1);
    }

    public Map<String, Long> getItemsCounts() {
        return new HashMap<>(this.inventory); // Restituisce una copia dell'inventario
    }
}
