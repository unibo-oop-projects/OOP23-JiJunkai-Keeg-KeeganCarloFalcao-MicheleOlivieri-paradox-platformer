package com.project.paradoxplatformer.utils.victory;

import java.util.ArrayList;
import java.util.List;

import com.project.paradoxplatformer.model.effect.api.Level;
import com.project.paradoxplatformer.model.player.PlayerModel;

public class VictoryConditionsFactoryImpl implements VictoryConditionsFactory{

    private PlayerModel player; 

    @Override
    public List<VictoryCondition> createVictoryConditionsForLevel(Level level, PlayerModel player) {
        this.player = player;
        return switch (level) {
            case LEVEL_ONE -> levelOneVictoryCondition();
            case LEVEL_TWO -> levelTwoVictoryCondition();
            case LEVEL_THREE -> levelThreeVictoryCondition();
            case LEVEL_FOUR -> levelFourVictoryCondition();
            default -> defaultVictoryCondition();
        };
    }

    public List<VictoryCondition> defaultVictoryCondition() {
        List<VictoryCondition> defaultList = new ArrayList();
        defaultList.add(new CoinCollectionVictoryCondition(this.player, 5)); // Just for test
        return defaultList;
    }

    private List<VictoryCondition> levelOneVictoryCondition() {
        return defaultVictoryCondition();
    }

    private List<VictoryCondition> levelTwoVictoryCondition() {
        return defaultVictoryCondition();
    }

    private List<VictoryCondition> levelThreeVictoryCondition() {
        return defaultVictoryCondition();
    }

    private List<VictoryCondition> levelFourVictoryCondition() {
        return defaultVictoryCondition();
    }

}
