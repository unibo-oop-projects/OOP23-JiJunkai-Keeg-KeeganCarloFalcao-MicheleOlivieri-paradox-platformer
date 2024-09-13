package com.project.paradoxplatformer.view;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.effect.ViewEventType;
import com.project.paradoxplatformer.utils.effect.api.Level;
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

<<<<<<< HEAD
    public void openView(PageIdentifier id, String param) {
        EventManager.getInstance().publish("SWITCH_VIEW", id, param);
=======
    public void openView(PageIdentifier id, Level param) throws InvalidResourceException {
        EventManager.getInstance().publish(ViewEventType.SWITCH_VIEW, id, param);
>>>>>>> abe30c815958be06d41f63bf38bd839d320068aa
    }

    public void openSettingsView() throws InvalidResourceException {
        openView(PageIdentifier.SETTINGS, Level.EMPTY_LEVEL);
    }

    public void goToMenu() {
        openView(PageIdentifier.MENU, "");
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
