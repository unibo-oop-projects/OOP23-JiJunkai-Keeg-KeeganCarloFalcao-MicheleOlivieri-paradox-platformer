package com.project.paradoxplatformer.model.endgame.condition;

import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.endgame.VictoryCondition;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * A victory condition that is met when the player reaches the start of the game
 * world.
 * The condition is satisfied when the player's X position is smaller than 0
 */
public class ReachStartVictoryCondition implements VictoryCondition {

    private final PlayerModel player;

    /**
     * Constructs a ReachEndVictoryCondition, which tracks the player's position
     * and checks if the player has reached the end of the world.
     *
     * @param player The player model used to track the player's position.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "This method needs exactly the original player.")
    public ReachStartVictoryCondition(final PlayerModel player) {
        this.player = player;
    }

    /**
     * Checks if the player has reached the start of the world.
     * The condition is met when the player's X position is smaller than the world's
     * width.
     *
     * @return true if the player has reached the start of the world, false
     *         otherwise.
     */
    @Override
    public boolean win() {
        return this.player.getPosition().x() < 0;
    }

}
