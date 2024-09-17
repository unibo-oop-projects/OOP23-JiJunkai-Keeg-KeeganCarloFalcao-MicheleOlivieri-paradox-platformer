package com.project.paradoxplatformer.model.player;

import java.util.Map;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;
import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.utils.StreamUtil;

/**
 * Such interface is targeted to manage the inventory for the player, so it acts
 * as a bridge to the player and the inventory.
 */
public interface InventoryManager {

    /**
     * Retrives an immutable inventory (defensive copy).
     * 
     * @return the inventory of the specified holder (e.g a player)
     */
    Inventory getInventory();

    /**
     * Collects an item and updates the inventory.
     * 
     * @param item the collectable item
     */
    void collectItem(CollectableGameObject item);

    /**
     * Utility function to get the number of collected coins from the inventory.
     * 
     * @return the number of coins
     */
    default long getCollectedCoins() {
        return this.getInventory().getItemsCounts()
                .entrySet()
                .stream()
                .filter(
                        StreamUtil.mapAndFilter(
                                Map.Entry::getKey,
                                Coin.class::isInstance))
                .findFirst()
                .map(Map.Entry::getValue) // Map to the value (number of coins)
                .orElse(0L); // Return 0 if "coins" is not present
    }
}
