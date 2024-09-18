package com.project.paradoxplatformer.model.endgame;

import java.util.Iterator;

/**
 * Manages the end game through
 * a collection of victory conditions and determines if any have been met
 * a collection of end conditions and determines if any have been met.
 */
public interface EndGameManager {

    /**
     * Checks all registered victory conditions to determine if a victory has been
     * achieved.
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

    /**
     * Checks all registered death conditions to determine if a victory has been
     * achieved.
     *
     * @return true if any death condition has been met, false otherwise.
     */
    boolean checkForDeath();

    /**
     * Called when a victory condition has been met to handle the death event.
     */
    void onDeath();

    /**
     * Sets the list of death conditions to be checked by the manager.
     *
     * @param newList The list of new death conditions.
     */
    void setDeathHandler(Iterator<DeathCondition> newList);

}
