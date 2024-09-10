package com.project.paradoxplatformer.view;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.utils.EventManager;

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

    public void openView(PageIdentifier id, String param) throws InvalidResourceException {
        EventManager.getInstance().publish("SWITCH_VIEW", id, param);
    }

    public void openSettingsView() throws InvalidResourceException {
        openView(PageIdentifier.SETTINGS, "");
    }

    public void goToLevelOne() throws InvalidResourceException {
        openView(PageIdentifier.GAME, "level1.json");
    }

    public void goToLevelTwo() throws InvalidResourceException {
        openView(PageIdentifier.GAME, "level2.json");
    }

    public void goToLevelThree() throws InvalidResourceException {
        openView(PageIdentifier.GAME, "level3.json");
    }

    public void goToLevelFour() throws InvalidResourceException {
        openView(PageIdentifier.GAME, "level4.json");
    }
}
