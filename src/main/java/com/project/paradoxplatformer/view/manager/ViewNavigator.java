package com.project.paradoxplatformer.view.manager;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.controller.event.EventManager;
import com.project.paradoxplatformer.controller.event.GameEventType;
import com.project.paradoxplatformer.controller.games.Level;

/**
 * ViewNavigator handles the navigation between different views in the
 * application.
 * It uses the EventManager to publish events that trigger view changes.
 */
public final class ViewNavigator {

    private static ViewNavigator instance; // Singleton instance of ViewNavigator

    // Private constructor to prevent direct instantiation
    private ViewNavigator() {
    }

    /**
     * Gets the singleton instance of the ViewNavigator.
     *
     * @return The singleton instance of ViewNavigator.
     */
    public static ViewNavigator getInstance() {
        if (instance == null) {
            synchronized (ViewNavigator.class) {
                if (instance == null) {
                    instance = new ViewNavigator();
                }
            }
        }
        return instance;
    }

    /**
     * Opens a view identified by the given PageIdentifier and Level.
     *
     * @param id    The identifier of the view to open.
     * @param param The level parameter to pass to the view.
     * @throws InvalidResourceException If the view resource is invalid or cannot be
     *                                  found.
     */
    public void openView(final PageIdentifier id, final Level param) throws InvalidResourceException {
        EventManager.getInstance().publish(GameEventType.SWITCH_VIEW, id, param);
    }

    /**
     * Opens the settings view.
     *
     * @throws InvalidResourceException If the view resource is invalid or cannot be
     *                                  found.
     */
    public void openSettingsView() throws InvalidResourceException {
        openView(PageIdentifier.SETTINGS, Level.EMPTY_LEVEL);
    }

    /**
     * Navigates to the main menu view.
     *
     * @throws InvalidResourceException If the view resource is invalid or cannot be
     *                                  found.
     */
    public void goToMenu() throws InvalidResourceException {
        openView(PageIdentifier.MENU, Level.EMPTY_LEVEL);
    }

    /**
     * Navigates to Level One of the game.
     *
     * @throws InvalidResourceException If the view resource is invalid or cannot be
     *                                  found.
     */
    public void goToLevelOne() throws InvalidResourceException {
        openView(PageIdentifier.GAME, Level.LEVEL_ONE);
    }

    /**
     * Navigates to Level Two of the game.
     *
     * @throws InvalidResourceException If the view resource is invalid or cannot be
     *                                  found.
     */
    public void goToLevelTwo() throws InvalidResourceException {
        openView(PageIdentifier.GAME, Level.LEVEL_TWO);
    }

    /**
     * Navigates to Level Three of the game.
     *
     * @throws InvalidResourceException If the view resource is invalid or cannot be
     *                                  found.
     */
    public void goToLevelThree() throws InvalidResourceException {
        openView(PageIdentifier.GAME, Level.LEVEL_THREE);
    }

    /**
     * Navigates to Level Four of the game.
     *
     * @throws InvalidResourceException If the view resource is invalid or cannot be
     *                                  found.
     */
    public void goToLevelFour() throws InvalidResourceException {
        openView(PageIdentifier.GAME, Level.LEVEL_FOUR);
    }
}
