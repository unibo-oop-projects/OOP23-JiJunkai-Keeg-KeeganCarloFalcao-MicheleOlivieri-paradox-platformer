package com.project.paradoxplatformer.utils.endGame;

/**
 * Represents a condition that determines if a player is death in the game.
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
