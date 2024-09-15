package com.project.paradoxplatformer.utils.victory;

import com.project.paradoxplatformer.model.player.PlayerModel;

public class CoinCollectionVictoryCondition implements VictoryCondition {

    private final int targetCoins;
    private final PlayerModel player; // Assume che il Player abbia un metodo per contare le monete raccolte

    public CoinCollectionVictoryCondition(PlayerModel player, int targetCoins) {
        this.player = player;
        this.targetCoins = targetCoins;
    }

    @Override
    public boolean Win() {
        return player.getCollectedCoins() >= this.targetCoins;
    }

}
