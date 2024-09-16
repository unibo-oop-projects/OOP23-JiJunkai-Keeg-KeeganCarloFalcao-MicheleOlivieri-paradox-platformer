package com.project.paradoxplatformer.utils.victory;

import java.util.Iterator;

/**
 * Implementation of the VictoryManager interface.
 * It manages and checks a collection of victory conditions.
 */
public class VictoryManagerImpl implements VictoryManager {

    private Iterator<VictoryCondition> conditions;

    /**
     * Constructs a VictoryManagerImpl with the specified list of conditions.
     *
     * @param conditions An iterator over the victory conditions to be managed.
     */
    public VictoryManagerImpl(Iterator<VictoryCondition> conditions) {
        this.conditions = conditions; 
    }

    /**
     * Checks each registered victory condition to determine if any have been met.
     *
     * @return true if a victory condition has been met, false otherwise.
     */
    @Override
    public boolean checkForVictory() {
        while (conditions.hasNext()) {
            VictoryCondition condition = conditions.next();
            if (condition.Win()) {
                onVictory();
                return true;
            }
        }
        return false;
    }

    /**
     * Called when a victory condition has been met. 
     * This method handles the victory event, such as displaying a win screen.
     */
    @Override
    public void onVictory() {
        // Actions to be taken upon victory, such as showing a victory screen or changing the level.
        System.out.println("Victory achieved!");
    }

    /**
     * Sets the iterator over the new victory conditions to be handled by the manager.
     *
     * @param newList An iterator over the new victory conditions.
     */
    @Override
    public void setVictoryHandler(Iterator<VictoryCondition> newList) {
        this.conditions = newList;
    }

}
