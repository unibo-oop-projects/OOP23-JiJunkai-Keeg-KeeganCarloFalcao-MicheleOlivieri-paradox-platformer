package com.project.paradoxplatformer.utils.victory;

import java.util.List;

import com.project.paradoxplatformer.model.effect.api.Level;
import com.project.paradoxplatformer.model.player.PlayerModel;

public interface VictoryConditionsFactory {

    List<VictoryCondition> createVictoryConditionsForLevel(Level level, PlayerModel player); 

}
