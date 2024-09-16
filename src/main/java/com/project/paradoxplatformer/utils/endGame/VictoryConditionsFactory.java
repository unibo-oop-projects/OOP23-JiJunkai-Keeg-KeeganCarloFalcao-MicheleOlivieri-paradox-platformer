package com.project.paradoxplatformer.utils.endGame;

import java.util.Iterator;

import com.project.paradoxplatformer.model.effect.api.Level;
import com.project.paradoxplatformer.model.player.PlayerModel;

/**
 * Interface for creating victory conditions for a specific level.
 */
public interface VictoryConditionsFactory {

    /**
     * Creates a list of victory conditions specific to the given level and player.
     *
     * @param level  The game level for which to create the victory conditions.
     * @param player The player model to use for conditions that depend on player state.
     * @return An iterator over the victory conditions for the specified level.
     */
    Iterator<VictoryCondition> createVictoryConditionsForLevel(Level level, PlayerModel player); 

    /**
     * Creates an iterator over default victory conditions, which can be used as a fallback.
     *
     * @return an iterator over the default victory conditions.
     */
    public Iterator<VictoryCondition> defaultVictoryCondition();

    /**
     * Defines the victory conditions for level one.
     *
     * @return an iterator over the victory conditions for level one.
     */
    public Iterator<VictoryCondition> levelOneVictoryCondition();

    /**
     * Defines the victory conditions for level two.
     *
     * @return an iterator over the victory conditions for level two.
     */
    public Iterator<VictoryCondition> levelTwoVictoryCondition();

    /**
     * Defines the victory conditions for level three.
     *
     * @return an iterator over the victory conditions for level three.
     */
    public Iterator<VictoryCondition> levelThreeVictoryCondition();

    /**
     * Defines the victory conditions for level four.
     *
     * @return an iterator over the victory conditions for level four.
     */
    public Iterator<VictoryCondition> levelFourVictoryCondition();

}
