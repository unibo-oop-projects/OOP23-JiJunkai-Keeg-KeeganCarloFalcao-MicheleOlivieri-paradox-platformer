package com.project.paradoxplatformer.utils.victory;

public class TriggerCollisionVictoryCondition implements VictoryCondition {

    private boolean isWinning;

    public TriggerCollisionVictoryCondition() {
        this.isWinning = false; 
    }

    public void setWinning(boolean value) {
        this.isWinning = value;
    }

    @Override
    public boolean Win() {
        return this.isWinning;
    }

}
