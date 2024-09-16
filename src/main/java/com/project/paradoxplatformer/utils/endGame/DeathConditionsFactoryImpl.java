package com.project.paradoxplatformer.utils.endGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.project.paradoxplatformer.model.effect.api.Level;
import com.project.paradoxplatformer.model.player.PlayerModel;

/**
 * DeathConditionsFactoryImpl generates different death conditions based on the game level 
 * and the player's state.
 * 
 * It uses the ConditionsFactory interface to provide specific death conditions for each level 
 * or default conditions when no specific ones are defined.
 */
public class DeathConditionsFactoryImpl implements ConditionsFactory<DeathCondition> {

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
    public Iterator<DeathCondition> createConditionsForLevel(Level level, PlayerModel player) {
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
     * Creates an iterator over default death conditions, which can be used as a fallback
     * when no specific conditions are required for a level.
     *
     * @return an iterator over the default death conditions.
     */
    @Override
    public Iterator<DeathCondition> defaultConditions() {
        List<DeathCondition> defaultList = new ArrayList<>();
        // Default condition: Player dies if health is below 0.
        defaultList.add(new HealthBelowZeroCondition(this.player));
        return defaultList.iterator();
    }

    /**
     * Defines the death conditions specific to level one.
     *
     * @return an iterator over the death conditions for level one.
     */
    @Override
    public Iterator<DeathCondition> levelOneConditions() {
        List<DeathCondition> conditions = new ArrayList<>();
        // Player dies if health is below 0.
        conditions.add(new HealthBelowZeroCondition(this.player));
        return conditions.iterator();
    }

    /**
     * Defines the death conditions specific to level two.
     *
     * @return an iterator over the death conditions for level two.
     */
    @Override
    public Iterator<DeathCondition> levelTwoConditions() {
        List<DeathCondition> conditions = new ArrayList<>();
        // Player dies if they exceed the time limit of 600 seconds.
        conditions.add(new TimeLimitDeathCondition(600));
        return conditions.iterator();
    }

    /**
     * Defines the death conditions specific to level three.
     *
     * @return an iterator over the death conditions for level three.
     */
    @Override
    public Iterator<DeathCondition> levelThreeConditions() {
        List<DeathCondition> conditions = new ArrayList<>();
        // Player dies if health is below 0.
        conditions.add(new HealthBelowZeroCondition(this.player));
        return conditions.iterator();
    }

    /**
     * Defines the death conditions specific to level four.
     *
     * @return an iterator over the death conditions for level four.
     */
    @Override
    public Iterator<DeathCondition> levelFourConditions() {
        List<DeathCondition> conditions = new ArrayList<>();
        // Player dies if health is below 0.
        conditions.add(new HealthBelowZeroCondition(this.player));
        return conditions.iterator();
    }

}
