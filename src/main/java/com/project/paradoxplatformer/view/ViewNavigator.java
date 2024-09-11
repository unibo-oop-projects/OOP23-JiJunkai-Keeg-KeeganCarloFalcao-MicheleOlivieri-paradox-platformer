package com.project.paradoxplatformer.view;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.Level;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.utils.EventManager;
import com.project.paradoxplatformer.utils.EventType;

public class ViewNavigator {

    private static ViewNavigator instance;

    private ViewNavigator() {
    }

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

    public void openView(PageIdentifier id, Level level) throws InvalidResourceException {
        System.out.println("Going to " + level);
        EventManager.getInstance().publish(EventType.SWITCH_VIEW, id, level);
    }

    public void openSettingsView() throws InvalidResourceException {
        openView(PageIdentifier.SETTINGS, Level.EMPTY_LEVEL);
    }

    public void goToLevelOne() throws InvalidResourceException {
        openView(PageIdentifier.GAME, Level.LEVEL_ONE);
    }

    public void goToLevelTwo() throws InvalidResourceException {
        openView(PageIdentifier.GAME, Level.LEVEL_TWO);
    }

    public void goToLevelThree() throws InvalidResourceException {
        openView(PageIdentifier.GAME, Level.LEVEL_THREE);
    }

    public void goToLevelFour() throws InvalidResourceException {
        openView(PageIdentifier.GAME, Level.LEVEL_FOUR);
    }
}
