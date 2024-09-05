package com.project.paradoxplatformer.view;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.utils.EventManager;

public class ViewNavigator {

    // Open a view based on PageIdentifier and optional parameter
    public void openView(PageIdentifier id, String param) throws InvalidResourceException {
        EventManager.getInstance().publish("SWITCH_VIEW", id, param);
    }

    // Convenience methods for specific views
    public void openSettingsView() throws InvalidResourceException {
        openView(PageIdentifier.SETTINGS, "");
    }

    public void goToLevelOne() throws InvalidResourceException {
        openView(PageIdentifier.LEVEL_ONE, "level1.json");
    }

    public void goToLevelTwo() throws InvalidResourceException {
        openView(PageIdentifier.LEVEL_TWO, "level2.json");
    }

    public void goToLevelThree() throws InvalidResourceException {
        openView(PageIdentifier.LEVEL_THREE, "level3.json");
    }
}
