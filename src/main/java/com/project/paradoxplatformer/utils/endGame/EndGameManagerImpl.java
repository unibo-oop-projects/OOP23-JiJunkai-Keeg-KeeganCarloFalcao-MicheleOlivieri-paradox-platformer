package com.project.paradoxplatformer.utils.endGame;

import java.util.Iterator;

/**
 * Implementation of the EndGameManager interface.
 * It manages and checks a collection of victory and death conditions.
 */
public class EndGameManagerImpl implements EndGameManager {

    private Iterator<VictoryCondition> victory;
    private Iterator<DeathCondition> death;

    /**
     * Constructs a EndGameManagerImpl with the specified list of conditions.
     *
     * @param conditions An iterator over the victory conditions to be managed.
     */
    public EndGameManagerImpl(Iterator<VictoryCondition> victory, Iterator<DeathCondition> death) {
        this.victory = victory; 
        this.death = death;
    }

    /**
     * Checks each registered victory condition to determine if any have been met.
     *
     * @return true if a victory condition has been met, false otherwise.
     */
    @Override
    public boolean checkForVictory() {
        while (victory.hasNext()) {
            VictoryCondition condition = victory.next();
            if (condition.Win()) {
                onVictory();
                return true;
            }
        }
        return false;
    }

    /**
     * Called when a victory condition has been met. 
     * This method handles the victory event, such as displaying a win screen.
     */
    @Override
    public void onVictory() {
        // Actions to be taken upon victory, such as showing a victory screen or changing the level.
        System.out.println("Victory achieved!");
    }

    /**
     * Checks each registered death condition to determine if any have been met.
     *
     * @return true if a victory condition has been met, false otherwise.
     */
    @Override
    public boolean checkForDeath() {
        while (death.hasNext()) {
            VictoryCondition condition = victory.next();
            if (condition.Win()) {
                onVictory();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDeath() {
        // Actions to be taken upon victory, such as showing a death screen or changing the level.
        System.out.println("Victory achieved!");
    }

    /**
     * Sets the iterator over the new victory conditions to be handled by the manager.
     *
     * @param newList An iterator over the new victory conditions.
     */
    @Override
    public void setVictoryHandler(Iterator<VictoryCondition> newList) {
        this.victory = newList;
    }

    /**
     * Sets the iterator over the new death conditions to be handled by the manager.
     *
     * @param newList An iterator over the new death conditions.
     */
    @Override
    public void setDeathHandler(Iterator<DeathCondition> newList) {
        this.death = newList;
    }

}
