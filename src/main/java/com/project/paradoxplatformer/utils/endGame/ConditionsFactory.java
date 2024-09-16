package com.project.paradoxplatformer.utils.endGame;

import java.util.Iterator;

import com.project.paradoxplatformer.model.effect.api.Level;
import com.project.paradoxplatformer.model.player.PlayerModel;

/**
 * The {@code ConditionsFactory} interface provides methods to create various 
 * types of conditions (e.g., death conditions, victory conditions) based on the 
 * game level and player state.
 *
 * @param <E> the type of condition this factory produces (e.g., {@code DeathCondition}, 
 *           {@code VictoryCondition}).
 */
public interface ConditionsFactory<E> {

    /**
     * Creates a list of conditions specific to the given level and player.
     * The conditions may vary based on the state of the player and the specifics of the level.
     *
     * @param level  The game level for which to create the conditions.
     * @param player The player model to use for conditions that depend on player state.
     * @return An iterator over the conditions for the specified level.
     */
    Iterator<E> createConditionsForLevel(Level level, PlayerModel player);

    /**
     * Creates an iterator over default conditions, which can be used as a fallback 
     * when no specific conditions are required for a level.
     *
     * @return an iterator over the default conditions.
     */
    Iterator<E> defaultConditions();

    /**
     * Defines the conditions specific to level one. This method provides a list of
     * conditions that are tailored for the first level of the game.
     *
     * @return an iterator over the conditions for level one.
     */
    Iterator<E> levelOneConditions();

    /**
     * Defines the conditions specific to level two. This method provides a list of
     * conditions that are tailored for the second level of the game.
     *
     * @return an iterator over the conditions for level two.
     */
    Iterator<E> levelTwoConditions();

    /**
     * Defines the conditions specific to level three. This method provides a list of
     * conditions that are tailored for the third level of the game.
     *
     * @return an iterator over the conditions for level three.
     */
    Iterator<E> levelThreeConditions();

    /**
     * Defines the conditions specific to level four. This method provides a list of
     * conditions that are tailored for the fourth level of the game.
     *
     * @return an iterator over the conditions for level four.
     */
    Iterator<E> levelFourConditions();
    
}
