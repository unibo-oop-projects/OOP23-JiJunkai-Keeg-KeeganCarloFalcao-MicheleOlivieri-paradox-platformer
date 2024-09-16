package com.project.paradoxplatformer.utils.victory;

import java.util.Iterator;

/**
 * Manages a collection of victory conditions and determines if any have been met.
 */
public interface VictoryManager {

    /**
     * Checks all registered victory conditions to determine if a victory has been achieved.
     *
     * @return true if any victory condition has been met, false otherwise.
     */
    boolean checkForVictory();

    /**
     * Called when a victory condition has been met to handle the victory event.
     */
    void onVictory();

    /**
     * Sets the list of victory conditions to be checked by the manager.
     *
     * @param newList The list of new victory conditions.
     */
    void setVictoryHandler(Iterator<VictoryCondition> newList);

}
