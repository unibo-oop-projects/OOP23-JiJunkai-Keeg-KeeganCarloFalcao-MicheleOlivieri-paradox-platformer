package com.project.paradoxplatformer.view.manager;

import com.project.paradoxplatformer.controller.event.EventManager;
import com.project.paradoxplatformer.controller.event.GameEventType;
import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

/**
 * ViewNavigator handles the navigation between different views in the
 * application.
 * It uses the EventManager to publish events that trigger view changes.
 */
public final class ViewNavigator {

    // Private constructor to prevent direct instantiation
    private ViewNavigator() {
    }

    /**
     * Gets the singleton instance of the ViewNavigator.
     *
     * @return The singleton instance of ViewNavigator.
     */
    public static ViewNavigator getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Opens a view identified by the given PageIdentifier and Level.
     *
     * @param id    The identifier of the view to open.
     * @param param The level parameter to pass to the view.
     */
    public void openView(final PageIdentifier id, final Level param) {
        EventManager.getInstance().publish(GameEventType.SWITCH_VIEW, id, param);
    }

    /**
     * Opens the settings view.
     */
    public void openSettingsView() {
        openView(PageIdentifier.SETTINGS, Level.EMPTY_LEVEL);
    }

    /**
     * Navigates to the main menu view.
     */
    public void goToMenu() {
        openView(PageIdentifier.MENU, Level.EMPTY_LEVEL);
    }

    /**
     * Navigates to Level One of the game.
     */
    public void goToLevelOne() {
        openView(PageIdentifier.GAME, Level.LEVEL_ONE);
    }

    /**
     * Navigates to Level Two of the game.
     */
    public void goToLevelTwo() {
        openView(PageIdentifier.GAME, Level.LEVEL_TWO);
    }

    /**
     * Navigates to Level Three of the game.
     */
    public void goToLevelThree() {
        openView(PageIdentifier.GAME, Level.LEVEL_THREE);
    }

    /**
     * Navigates to Level Four of the game.
     */
    public void goToLevelFour() {
        openView(PageIdentifier.GAME, Level.LEVEL_FOUR);
    }

    // Holder class for lazy initialization
    private static final class Holder {
        private static final ViewNavigator INSTANCE = new ViewNavigator();
    }
}
