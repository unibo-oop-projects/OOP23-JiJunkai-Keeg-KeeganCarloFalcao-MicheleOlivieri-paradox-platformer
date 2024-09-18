package com.project.paradoxplatformer.model.endgame.condition;

import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.endgame.VictoryCondition;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * The CoinCollectionVictoryCondition class represents a victory condition where
 * the player must collect a specific number of coins to win.
 */
public class CoinCollectionVictoryCondition implements VictoryCondition {

    private final int targetCoins;
    private final PlayerModel player;

    /**
     * Constructs a CoinCollectionVictoryCondition.
     *
     * @param player      The player model, used to track the number of coins
     *                    collected.
     * @param targetCoins The number of coins the player must collect to win.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "This method needs exactly the original player.")
    public CoinCollectionVictoryCondition(final PlayerModel player, final int targetCoins) {
        this.player = player;
        this.targetCoins = targetCoins;
    }

    /**
     * Checks if the player has won by collecting the required number of coins.
     *
     * @return true if the player has collected at least the target number of coins,
     *         false otherwise.
     */
    @Override
    public boolean win() {
        return player.getCollectedCoins() >= this.targetCoins;
    }

}
