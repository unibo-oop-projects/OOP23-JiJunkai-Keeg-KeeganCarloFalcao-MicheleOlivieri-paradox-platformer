package com.project.paradoxplatformer.utils.victory;

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

}
