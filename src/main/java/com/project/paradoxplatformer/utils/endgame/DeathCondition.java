package com.project.paradoxplatformer.utils.endgame;

/**
 * Represents a condition that determines if a player is dead in the game.
 * Classes implementing this interface define specific death conditions.
 */
public interface DeathCondition {

    /**
     * Checks if the death condition has been met.
     *
     * @return true if the condition has been met, false otherwise.
     */
    boolean death();
}
