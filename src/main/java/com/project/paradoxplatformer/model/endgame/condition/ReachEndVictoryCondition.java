package com.project.paradoxplatformer.model.endgame.condition;

import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.endgame.VictoryCondition;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * A victory condition that is met when the player reaches the end of the game
 * world.
 * The condition is satisfied when the player's X position exceeds the width of
 * the world.
 */
public class ReachEndVictoryCondition implements VictoryCondition {

    private static final int END_WORLD = 600; // TO FIX With real value
    private final PlayerModel player;

    /**
     * Constructs a ReachEndVictoryCondition, which tracks the player's position
     * and checks if the player has reached the end of the world.
     *
     * @param player The player model used to track the player's position.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "This method needs exactly the original player.")
    public ReachEndVictoryCondition(final PlayerModel player) {
        this.player = player;
    }

    /**
     * Checks if the player has reached the end of the world.
     * The condition is met when the player's X position is greater than the world's
     * width.
     *
     * @return true if the player has reached the end of the world, false otherwise.
     */
    @Override
    public boolean win() {
        return this.player.getPosition().x() > ReachEndVictoryCondition.END_WORLD;
    }

}
