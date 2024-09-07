package com.project.paradoxplatformer.view;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.utils.EventManager;

public class ViewNavigator {

    // Switches to a specific view based on PageIdentifier and an optional parameter
    // by triggering the View Manager
    public void openView(PageIdentifier id, String param) throws InvalidResourceException {
        EventManager.getInstance().publish("SWITCH_VIEW", id, param); // Publish an event to switch views
    }

    // Convenience method to open the settings view
    public void openSettingsView() throws InvalidResourceException {
        openView(PageIdentifier.SETTINGS, ""); // Open settings view
    }

    // Convenience method to open level one with a specific resource file
    public void goToLevelOne() throws InvalidResourceException {
        openView(PageIdentifier.GAME, "level1.json"); // Open level one view with corresponding JSON file
    }

    // Convenience method to open level two with a specific resource file
    public void goToLevelTwo() throws InvalidResourceException {
        openView(PageIdentifier.GAME, "level2.json"); // Open level two view with corresponding JSON file
    }

    // Convenience method to open level three with a specific resource file
    public void goToLevelThree() throws InvalidResourceException {
        openView(PageIdentifier.GAME, "level3.json"); // Open level three view with corresponding JSON file
    }
}
