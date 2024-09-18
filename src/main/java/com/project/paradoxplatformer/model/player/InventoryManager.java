package com.project.paradoxplatformer.model.player;

import java.util.Map;

import com.project.paradoxplatformer.model.entity.CollectableGameObject;
import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.utils.StreamUtil;

/**
 * An interface for managing a player's inventory, serving as a bridge between the player 
 * and their inventory system. This interface provides methods to access the inventory, 
 * collect items, and retrieve specific item counts, such as coins.
 * 
 * <p>Implementations of this interface handle operations related to the inventory, including 
 * adding items and querying the inventory state. It is typically used in game systems where 
 * a player has an inventory of collectible items.
 * </p>
 */
public interface InventoryManager {

    /**
     * Retrieves a defensive copy of the inventory, ensuring that the original inventory
     * cannot be modified directly through this method.
     * 
     * <p>This method returns an immutable view of the inventory, which represents the current 
     * state of items held by the player. The defensive copy protects the integrity of the 
     * inventory by preventing external modifications.</p>
     * 
     * @return an {@link Inventory} object representing the player's current inventory.
     */
    Inventory getInventory();

    /**
     * Collects a specified item and updates the inventory to reflect this change.
     * 
     * <p>This method adds the given {@link CollectableGameObject} item to the player's inventory. 
     * The inventory is updated to include the newly collected item, and any necessary changes are 
     * made to reflect this addition.</p>
     * 
     * @param item the {@link CollectableGameObject} that is to be collected and added to the inventory.
     * @throws IllegalArgumentException if the item is null or invalid.
     */
    void collectItem(CollectableGameObject item);

    /**
     * Retrieves the total number of collected coins from the inventory.
     * 
     * <p>This default method provides a convenient way to count the number of coins held in the 
     * inventory. It works by filtering the items in the inventory to find instances of {@link Coin}, 
     * and then summing their counts. If no coins are present, it returns zero.</p>
     * 
     * @return the total number of coins in the inventory. If no coins are present, returns 0.
     */
    default long getCollectedCoins() {
        return this.getInventory().getItemsCounts()
                .entrySet()
                .stream()
                .filter(
                    StreamUtil.mapAndFilter(
                        Map.Entry::getKey,
                        Coin.class.getSimpleName()::equals
                    )
                )
                .findFirst()
                .map(Map.Entry::getValue) // Map to the value (number of coins)
                .orElse(0L); // Return 0 if "coins" is not present
    }
}
