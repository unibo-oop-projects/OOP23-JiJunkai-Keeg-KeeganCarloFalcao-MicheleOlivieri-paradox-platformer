package com.project.paradoxplatformer.utils.endGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.project.paradoxplatformer.model.effect.api.Level;
import com.project.paradoxplatformer.model.player.PlayerModel;

/**
 * VictoryConditionsFactoryImpl generates different victory conditions for each level.
 * It allows the dynamic creation of conditions based on the level and player model.
 */
public class VictoryConditionsFactoryImpl implements VictoryConditionsFactory {

    private PlayerModel player;

    /**
     * Creates an iterator of victory conditions for the specified level.
     * Each level can have unique conditions or default ones.
     *
     * @param level  the current game level.
     * @param player the player model used to track game state.
     * @return an iterator over victory conditions specific to the level.
     */
    @Override
    public Iterator<VictoryCondition> createVictoryConditionsForLevel(Level level, PlayerModel player) {
        this.player = player;
        return switch (level) {
            case LEVEL_ONE -> levelOneVictoryCondition();
            case LEVEL_TWO -> levelTwoVictoryCondition();
            case LEVEL_THREE -> levelThreeVictoryCondition();
            case LEVEL_FOUR -> levelFourVictoryCondition();
            default -> defaultVictoryCondition();
        };
    }

    /**
     * Creates an iterator over default victory conditions, which can be used as a fallback.
     *
     * @return an iterator over the default victory conditions.
     */
    @Override
    public Iterator<VictoryCondition> defaultVictoryCondition() {
        List<VictoryCondition> defaultList = new ArrayList<>();
        // Example: Collect 5 coins to win.
        defaultList.add(new CoinCollectionVictoryCondition(this.player, 5)); 
        return defaultList.iterator();
    }

    /**
     * Defines the victory conditions for level one.
     *
     * @return an iterator over the victory conditions for level one.
     */
    @Override
    public Iterator<VictoryCondition> levelOneVictoryCondition() {
        List<VictoryCondition> conditions = new ArrayList<>();
        conditions.add(new CoinCollectionVictoryCondition(this.player, 10)); // Collect 10 coins
        conditions.add(new TimeLimitVictoryCondition(300)); // Win by surviving for 300 seconds
        return conditions.iterator();
    }

    /**
     * Defines the victory conditions for level two.
     *
     * @return an iterator over the victory conditions for level two.
     */
    @Override
    public Iterator<VictoryCondition> levelTwoVictoryCondition() {
        List<VictoryCondition> conditions = new ArrayList<>();
        conditions.add(new TriggerCollisionVictoryCondition()); // Reach specific end game level
        return conditions.iterator();
    }

    /**
     * Defines the victory conditions for level three.
     *
     * @return an iterator over the victory conditions for level three.
     */
    @Override
    public Iterator<VictoryCondition> levelThreeVictoryCondition() {
        List<VictoryCondition> conditions = new ArrayList<>();
        conditions.add(new CoinCollectionVictoryCondition(this.player, 15)); // Collect 15 coins
        conditions.add(new TimeLimitVictoryCondition(300)); // Win by surviving for 300 seconds
        return conditions.iterator();
    }

    /**
     * Defines the victory conditions for level four.
     *
     * @return an iterator over the victory conditions for level four.
     */
    @Override
    public Iterator<VictoryCondition> levelFourVictoryCondition() {
        List<VictoryCondition> conditions = new ArrayList<>();
        conditions.add(new TimeLimitVictoryCondition(400)); // Survive for 400 seconds
        return conditions.iterator();
    }

}
