package com.project.paradoxplatformer.utils.endGame;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import com.project.paradoxplatformer.controller.event.EventManager;
import com.project.paradoxplatformer.controller.event.GameEventType;
import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.utils.ListUtil;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.view.manager.ViewNavigator;

/**
 * Implementation of the EndGameManager interface.
 * It manages and checks a collection of victory and death conditions.
 */
public class EndGameManagerImpl implements EndGameManager {

    private List<VictoryCondition> victory;
    private List<DeathCondition> death;
    private final Level nextLevel;
    private final Level level;

    /**
     * Constructs a EndGameManagerImpl with the specified list of conditions.
     *
     * @param victory An iterator over the victory conditions to be managed.
     * @param death   An iterator over the death conditions to be managed.
     * @param level   The current level being managed.
     */
    public EndGameManagerImpl(Level level) {
        this.victory = ListUtil.toList(new VictoryConditionsFactoryImpl().defaultConditions());
        this.death = ListUtil.toList(new DeathConditionsFactoryImpl().defaultConditions());
        this.nextLevel = level.next();
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
        triggerEvent("Victory achieved!", PageIdentifier.MENU);
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
        triggerEvent("Death achieved!", PageIdentifier.GAME);
    }

    /**
     * Sets the iterator over the new victory conditions to be handled by the
     * manager.
     *
     * @param newList An iterator over the new victory conditions.
     */
    @Override
    public void setVictoryHandler(Iterator<VictoryCondition> newList) {
        this.victory = ListUtil.toList(newList);
    }

    /**
     * Sets the iterator over the new death conditions to be handled by the manager.
     *
     * @param newList An iterator over the new death conditions.
     */
    @Override
    public void setDeathHandler(Iterator<DeathCondition> newList) {
        this.death = ListUtil.toList(newList);
    }

    /**
     * General method to check a condition iterator, execute an action on success.
     *
     * @param iterator  The condition iterator.
     * @param condition The condition to check.
     * @param onSuccess The action to execute on success.
     * @return true if the condition was met, false otherwise.
     */
    private <T> boolean checkCondition(List<T> list, Predicate<T> condition,
            Runnable onSuccess) {
        boolean result = list.stream().anyMatch(condition);
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
    private void triggerEvent(String message, PageIdentifier page) {
        System.out.println(message);
        if (page != null) {
            EventManager.getInstance().publish(GameEventType.STOP_VIEW, page, null);
            if (page.equals(PageIdentifier.GAME)) {
                System.out.println("IN THE GAME");
                if (message.equals("Death achieved!")) {
                    ViewNavigator.getInstance().openView(page, this.level);
                }
            } else {
                System.out.println("GO TO MENU");
                ViewNavigator.getInstance().goToMenu();
            }
        }
    }
}
