package com.project.paradoxplatformer.utils.endgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.model.player.PlayerModel;

/**
 * DeathConditionsFactoryImpl generates different death conditions based on the
 * game level
 * and the player's state.
 * 
 * It uses the ConditionsFactory interface to provide specific death conditions
 * for each level
 * or default conditions when no specific ones are defined.
 */
public class DeathConditionsFactoryImpl implements ConditionsFactory<DeathCondition> {

    private static final int TIME_LIMIT = 600; // Constant for time limit in seconds
    private PlayerModel player;

    /**
     * Creates an iterator of death conditions for the specified level.
     * Each level can have unique conditions or default ones.
     *
     * @param level  the current game level.
     * @param player the player model used to track game state.
     * @return an iterator over death conditions specific to the level.
     */
    @Override
    public Iterator<DeathCondition> createConditionsForLevel(final Level level, final PlayerModel player) {
        if (player == null) {
            return defaultConditions();
        }
        this.player = Optional.of(player).get();
        return switch (level) {
            case LEVEL_ONE -> levelOneConditions();
            case LEVEL_TWO -> levelTwoConditions();
            case LEVEL_THREE -> levelThreeConditions();
            case LEVEL_FOUR -> levelFourConditions();
            default -> defaultConditions();
        };
    }

    /**
     * Creates an iterator over default death conditions, which can be used as a
     * fallback
     * when no specific conditions are required for a level.
     *
     * @return an iterator over the default death conditions.
     */
    @Override
    public Iterator<DeathCondition> defaultConditions() {
        final List<DeathCondition> defaultList = new ArrayList<>();
        // Default condition: Player dies if health is below 0.
        // defaultList.add(new FallenCondition(this.player));
        return defaultList.iterator();
    }

    /**
     * Defines the death conditions specific to level one.
     *
     * @return an iterator over the death conditions for level one.
     */
    @Override
    public Iterator<DeathCondition> levelOneConditions() {
        final List<DeathCondition> conditions = new ArrayList<>();
        // Player dies if health is below 0.
        conditions.add(new FallenCondition(this.player));
        return conditions.iterator();
    }

    /**
     * Defines the death conditions specific to level two.
     *
     * @return an iterator over the death conditions for level two.
     */
    @Override
    public Iterator<DeathCondition> levelTwoConditions() {
        final List<DeathCondition> conditions = new ArrayList<>();
        // Player dies if they exceed the time limit of 600 seconds.
        conditions.add(new TimeLimitDeathCondition(TIME_LIMIT));
        return conditions.iterator();
    }

    /**
     * Defines the death conditions specific to level three.
     *
     * @return an iterator over the death conditions for level three.
     */
    @Override
    public Iterator<DeathCondition> levelThreeConditions() {
        final List<DeathCondition> conditions = new ArrayList<>();
        // Player dies if health is below 0.
        conditions.add(new FallenCondition(this.player));
        return conditions.iterator();
    }

    /**
     * Defines the death conditions specific to level four.
     *
     * @return an iterator over the death conditions for level four.
     */
    @Override
    public Iterator<DeathCondition> levelFourConditions() {
        final List<DeathCondition> conditions = new ArrayList<>();
        // Player dies if health is below 0.
        conditions.add(new FallenCondition(this.player));
        conditions.add(new DeathObstacleCollisionCondition());
        return conditions.iterator();
    }
}
