package com.project.paradoxplatformer.utils.endGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.model.player.PlayerModel;

/**
 * VictoryConditionsFactoryImpl generates different victory conditions for each
 * level. It allows the dynamic creation of conditions based on the level and player
 * model.
 */
public class VictoryConditionsFactoryImpl implements ConditionsFactory<VictoryCondition> {

    private static final int LEVEL_ONE_COIN_COUNT = 10;
    private static final int LEVEL_THREE_COIN_COUNT = 15;
    private static final int LEVEL_ONE_TIME_LIMIT = 300;
    private static final int LEVEL_THREE_TIME_LIMIT = 300;
    private static final int LEVEL_FOUR_TIME_LIMIT = 400;
    private static final int DEFAULT_COIN_COUNT = 5;

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
    public Iterator<VictoryCondition> createConditionsForLevel(final Level level, final PlayerModel player) {
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
     * Creates an iterator over default victory conditions, which can be used as a
     * fallback.
     *
     * @return an iterator over the default victory conditions.
     */
    @Override
    public Iterator<VictoryCondition> defaultConditions() {
        List<VictoryCondition> defaultList = new ArrayList<>();
        defaultList.add(new ReachEndVictoryCondition(this.player)); // Reach specific end game level
        defaultList.add(new CoinCollectionVictoryCondition(this.player, DEFAULT_COIN_COUNT));
        System.err.println("" + defaultList.toString());
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
        conditions.add(new ReachEndVictoryCondition(this.player)); // Reach specific end game level
        conditions.add(new CoinCollectionVictoryCondition(this.player, LEVEL_ONE_COIN_COUNT)); // Collect 10 coins
        conditions.add(new TimeLimitVictoryCondition(LEVEL_ONE_TIME_LIMIT)); // Win by surviving for 300 seconds
        System.err.println("" + conditions.toString());
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
        conditions.add(new ReachEndVictoryCondition(this.player)); // Reach specific end game level
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
        conditions.add(new ReachEndVictoryCondition(this.player)); // Reach specific end game level
        conditions.add(new CoinCollectionVictoryCondition(this.player, LEVEL_THREE_COIN_COUNT)); // Collect 15 coins
        conditions.add(new TimeLimitVictoryCondition(LEVEL_THREE_TIME_LIMIT)); // Win by surviving for 300 seconds
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
        conditions.add(new ReachEndVictoryCondition(this.player)); // Reach specific end game level
        conditions.add(new TimeLimitVictoryCondition(LEVEL_FOUR_TIME_LIMIT)); // Survive for 400 seconds
        return conditions.iterator();
    }

}
