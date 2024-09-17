package com.project.paradoxplatformer.utils.endGame;

import java.util.Iterator;

import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.utils.StreamUtil;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.view.manager.ViewNavigator;

/**
 * Implementation of the EndGameManager interface.
 * It manages and checks a collection of victory and death conditions.
 */
public class EndGameManagerImpl implements EndGameManager {

    private Iterator<VictoryCondition> victory;
    private Iterator<DeathCondition> death;
    private final Level level;

    /**
     * Constructs a EndGameManagerImpl with the specified list of conditions.
     *
     * @param level   The current level being managed.
     */
    public EndGameManagerImpl(final Level level) {
        this.victory = new VictoryConditionsFactoryImpl().defaultConditions();
        this.death = new DeathConditionsFactoryImpl().defaultConditions();
        this.level = level;
    }

    /**
     * Checks each registered victory condition to determine if any have been met.
     *
     * @return true if a victory condition has been met, false otherwise.
     */
    @Override
    public boolean checkForVictory() {
        return checkCondition(victory, VictoryCondition::Win, this::onVictory);
    }

    /**
     * Called when a victory condition has been met.
     * This method handles the victory event, such as displaying a win screen.
     */
    @Override
    public void onVictory() {
        triggerEvent("Victory achieved!", PageIdentifier.GAME);
    }

    /**
     * Checks each registered death condition to determine if any have been met.
     *
     * @return true if a death condition has been met, false otherwise.
     */
    @Override
    public boolean checkForDeath() {
        return checkCondition(death, DeathCondition::death, this::onDeath);
    }

    /**
     * Called when a death condition has been met.
     * This method handles the death event, such as displaying a death screen.
     */
    @Override
    public void onDeath() {
        triggerEvent("Death achieved!", null);
    }

    /**
     * Sets the iterator over the new victory conditions to be handled by the
     * manager.
     *
     * @param newList An iterator over the new victory conditions.
     */
    @Override
    public void setVictoryHandler(final Iterator<VictoryCondition> newList) {
        this.victory = newList;
    }

    /**
     * Sets the iterator over the new death conditions to be handled by the manager.
     *
     * @param newList An iterator over the new death conditions.
     */
    @Override
    public void setDeathHandler(final Iterator<DeathCondition> newList) {
        this.death = newList;
    }

    /**
     * General method to check a condition iterator, execute an action on success.
     *
     * @param iterator  The condition iterator.
     * @param condition The condition to check.
     * @param onSuccess The action to execute on success.
     * @param <T> type of condition
     * @return true if the condition was met, false otherwise.
     */
    private <T> boolean checkCondition(final Iterator<T> iterator, final java.util.function.Predicate<T> condition,
            final Runnable onSuccess) {
        final boolean result = StreamUtil.toStream(iterator).anyMatch(condition);
        if (result) {
            onSuccess.run();
        }
        return result;
    }

    /**
     * Triggers an event with a given message and navigates to the specified page.
     *
     * @param message The message to print.
     * @param page    The page identifier to navigate to (can be null).
     */
    private void triggerEvent(final String message, final PageIdentifier page) {
        System.out.println(message);
        if (page != null) {
            ViewNavigator.getInstance().openView(page, this.level);
        }
    }
}
