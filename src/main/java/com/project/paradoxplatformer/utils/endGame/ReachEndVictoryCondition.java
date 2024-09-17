package com.project.paradoxplatformer.utils.endGame;

import com.project.paradoxplatformer.model.player.PlayerModel;

/**
 * A victory condition that is met when the player reaches the end of the game world.
 * The condition is satisfied when the player's X position exceeds the width of the world.
 */
public class ReachEndVictoryCondition implements VictoryCondition {

    private final static int END_WORLD = 600; //TO FIX With real value
    private final PlayerModel player;

    /**
     * Constructs a ReachEndVictoryCondition, which tracks the player's position
     * and checks if the player has reached the end of the world.
     *
     * @param player The player model used to track the player's position.
     */
    public ReachEndVictoryCondition(final PlayerModel player) {
        this.player = player;
    }

    /**
     * Checks if the player has reached the end of the world.
     * The condition is met when the player's X position is greater than the world's width.
     *
     * @return true if the player has reached the end of the world, false otherwise.
     */
    @Override
    public boolean Win() {
        return this.player.getPosition().x() > ReachEndVictoryCondition.END_WORLD;
    }

}
