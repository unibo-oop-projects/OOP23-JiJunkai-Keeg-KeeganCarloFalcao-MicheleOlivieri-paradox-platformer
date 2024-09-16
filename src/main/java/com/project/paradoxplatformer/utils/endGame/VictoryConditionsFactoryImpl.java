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
public class VictoryConditionsFactoryImpl implements ConditionsFactory<VictoryCondition> {

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
    public Iterator<VictoryCondition> createConditionsForLevel(Level level, PlayerModel player) {
        this.player = player;
        return switch (level) {
            case LEVEL_ONE -> levelOneConditions();
            case LEVEL_TWO -> levelTwoConditions();
            case LEVEL_THREE -> levelThreeConditions();
            case LEVEL_FOUR -> levelFourConditions();
            default -> defaultConditions();
        };
    }

    /**
     * Creates an iterator over default victory conditions, which can be used as a fallback.
     *
     * @return an iterator over the default victory conditions.
     */
    @Override
    public Iterator<VictoryCondition> defaultConditions() {
        List<VictoryCondition> defaultList = new ArrayList<>();
        defaultList.add(new TriggerCollisionVictoryCondition()); // Reach specific end game level
        defaultList.add(new CoinCollectionVictoryCondition(this.player, 5)); 
        System.err.println(""+defaultList.toString());
        return defaultList.iterator();
    }

    /**
     * Defines the victory conditions for level one.
     *
     * @return an iterator over the victory conditions for level one.
     */
    @Override
    public Iterator<VictoryCondition> levelOneConditions() {
        List<VictoryCondition> conditions = new ArrayList<>();
        conditions.add(new TriggerCollisionVictoryCondition()); // Reach specific end game level
        conditions.add(new CoinCollectionVictoryCondition(this.player, 10)); // Collect 10 coins
        conditions.add(new TimeLimitVictoryCondition(300)); // Win by surviving for 300 seconds
        System.err.println(""+conditions.toString());
        return conditions.iterator();
    }

    /**
     * Defines the victory conditions for level two.
     *
     * @return an iterator over the victory conditions for level two.
     */
    @Override
    public Iterator<VictoryCondition> levelTwoConditions() {
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
    public Iterator<VictoryCondition> levelThreeConditions() {
        List<VictoryCondition> conditions = new ArrayList<>();
        conditions.add(new TriggerCollisionVictoryCondition()); // Reach specific end game level
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
    public Iterator<VictoryCondition> levelFourConditions() {
        List<VictoryCondition> conditions = new ArrayList<>();
        conditions.add(new TriggerCollisionVictoryCondition()); // Reach specific end game level
        conditions.add(new TimeLimitVictoryCondition(400)); // Survive for 400 seconds
        return conditions.iterator();
    }

}
