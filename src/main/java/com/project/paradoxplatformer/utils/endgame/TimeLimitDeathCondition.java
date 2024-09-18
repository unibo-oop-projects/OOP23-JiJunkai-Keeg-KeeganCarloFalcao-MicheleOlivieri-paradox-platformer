package com.project.paradoxplatformer.utils.endgame;

/**
 * TimeLimitDeathCondition checks if the player has survived past a specific
 * time limit.
 */
public class TimeLimitDeathCondition implements DeathCondition {

    private final long startTime;
    private final long timeLimit;

    /**
     * Constructs a TimeLimitDeathCondition with the specified time limit.
     *
     * @param timeLimit the time limit in seconds after which the player dies.
     */
    public TimeLimitDeathCondition(final long timeLimit) {
        this.startTime = System.currentTimeMillis();
        this.timeLimit = timeLimit * 1000; // Convert seconds to milliseconds
    }

    /**
     * Checks if the time limit has been exceeded.
     *
     * @return true if the time limit has been exceeded, false otherwise.
     */
    @Override
    public boolean death() {
        final long elapsedTime = System.currentTimeMillis() - startTime;
        return elapsedTime > timeLimit;
    }

}
