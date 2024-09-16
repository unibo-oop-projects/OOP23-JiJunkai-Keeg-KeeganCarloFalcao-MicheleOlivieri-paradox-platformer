package com.project.paradoxplatformer.utils.victory;

/**
 * A victory condition that is triggered by a specific in-game collision.
 * The condition is met when an external event sets it as "winning".
 */
public class TriggerCollisionVictoryCondition implements VictoryCondition {

    private boolean isWinning;

    /**
     * Constructs a TriggerCollisionVictoryCondition with the initial state set to false.
     */
    public TriggerCollisionVictoryCondition() {
        this.isWinning = false; 
    }

    /**
     * Sets the winning state of this condition.
     *
     * @param value true to set the condition as won, false otherwise.
     */
    public void setWinning(boolean value) {
        this.isWinning = value;
    }

    /**
     * Checks if the victory condition has been met.
     *
     * @return true if the condition has been triggered, false otherwise.
     */
    @Override
    public boolean Win() {
        return this.isWinning;
    }

}
