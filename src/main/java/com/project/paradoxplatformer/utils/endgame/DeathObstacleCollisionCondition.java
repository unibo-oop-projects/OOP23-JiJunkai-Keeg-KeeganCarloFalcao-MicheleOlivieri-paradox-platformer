package com.project.paradoxplatformer.utils.endgame;

// import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * A static implementation of the DeathCondition interface that tracks
 * if a player is dead based on collision with a death obstacle.
 */
public class DeathObstacleCollisionCondition implements DeathCondition {

    // Static state to track if the player is dead.
    private static boolean isDead;

    /**
     * Constructor initializing the death state to false.
     */
    // @SuppressFBWarnings(value = { "ST" }, justification = "For a simply call.")
    public DeathObstacleCollisionCondition() {
        isDead = false;
    }

    /**
     * Checks if the death condition has been met.
     *
     * @return true if the player is dead, false otherwise.
     */
    @Override
    public boolean death() {
        return isDead;
    }

    /**
     * Sets the death state of the player.
     * This method will be called by an observer when a collision with a
     * deathObstacle occurs.
     *
     * @param dead true if the player has collided with a death obstacle, false
     *             otherwise.
     */
    public static void setDeath(final boolean dead) {
        isDead = dead;
    }
}
