package com.project.paradoxplatformer.utils.endGame;

import com.project.paradoxplatformer.model.player.PlayerModel;

/**
 * HealthBelowZeroCondition checks if the player's health has fallen below zero.
 */
public class HealthBelowZeroCondition implements DeathCondition {

    private final PlayerModel player;

    /**
     * Constructs a HealthBelowZeroCondition with the specified player model.
     *
     * @param player the player model to check the health of.
     */
    public HealthBelowZeroCondition(PlayerModel player) {
        this.player = player;
    }

    /**
     * Checks if the player's health is below zero.
     *
     * @return true if the player's health is below zero, false otherwise.
     */
    @Override
    public boolean death() {
        return player.getPosition().y() <= 0;
    }

}
