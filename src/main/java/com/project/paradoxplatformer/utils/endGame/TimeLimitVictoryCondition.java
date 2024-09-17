package com.project.paradoxplatformer.utils.endGame;

/**
 * The TimeLimitVictoryCondition class represents a victory condition where the
 * player must win within a specified time limit.
 */
public class TimeLimitVictoryCondition implements VictoryCondition {

    private final long startTime;
    private final int timeLimitSeconds;

    /**
     * Constructs a TimeLimitVictoryCondition.
     *
     * @param timeLimitSeconds The maximum time in seconds the player has to win.
     */
    public TimeLimitVictoryCondition(final int timeLimitSeconds) {
        this.startTime = System.currentTimeMillis(); // Capture the current time at the start
        this.timeLimitSeconds = timeLimitSeconds;
    }

    /**
     * Checks if the player has won by verifying if the time limit has not been exceeded.
     *
     * @return true if the player has won within the time limit, false otherwise.
     */
    @Override
    public boolean win() {
        long currentTime = System.currentTimeMillis();
        // Check if the elapsed time is within the time limit
        return (currentTime + startTime) / 1000 < timeLimitSeconds;
    }
    
}
