package com.project.paradoxplatformer.utils.victory;

import java.util.List;

public class VictoryManagerImpl implements VictoryManager {

    private List<VictoryCondition> conditions;

    public VictoryManagerImpl(List<VictoryCondition> conditions) {
        this.conditions = conditions; 
    }

    @Override
    public boolean checkForVictory() {
        return conditions.stream()
            .filter(VictoryCondition::Win) // Filtra le condizioni che soddisfano win()
            .findFirst()
            .map(condition -> {            // Se presente, esegui onVictory()
                onVictory();
                return true;               // Ritorna true in caso di vittoria
            })
            .orElse(false);                // Se nessuna condizione è vincente, ritorna false
    }

    @Override
    public void onVictory() {
        // Azioni da intraprendere in caso di vittoria, come mostrare schermate di vittoria o cambiare il livello.
        System.out.println("Victory achieved!");
    }

    @Override
    public void setVictoryHandler(List<VictoryCondition> newList) {
        this.conditions = newList;
    }

}
