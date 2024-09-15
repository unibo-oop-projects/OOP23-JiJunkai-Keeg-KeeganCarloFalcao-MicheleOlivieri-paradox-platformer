package com.project.paradoxplatformer.utils.victory;

import java.util.List;

public interface VictoryManager {
    
    boolean checkForVictory();

    void onVictory();

    void setVictoryHandler(List<VictoryCondition> newList);

}
