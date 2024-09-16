package com.project.paradoxplatformer.utils.endGame;

/**
 * Represents a condition that determines if a player has won the game.
 * Classes implementing this interface define specific victory conditions.
 */
public interface VictoryCondition {

    /**
     * Checks if the victory condition has been met.
     *
     * @return true if the condition has been met, false otherwise.
     */
    boolean Win();

}
