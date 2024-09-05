package com.project.paradoxplatformer;

import java.net.URL;
import java.util.ResourceBundle;

import com.project.paradoxplatformer.utils.EventManager;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.view.ElementDecorator;
import com.project.paradoxplatformer.view.EventBinder;
import com.project.paradoxplatformer.view.Page;
import com.project.paradoxplatformer.view.ViewNavigator;
import com.project.paradoxplatformer.view.legacy.ViewLegacy;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class NewLevelController implements Initializable, Page<String> {

    @FXML
    private Button settingsButton;

    @FXML
    private Circle levelOneButton, levelTwoButton, levelThreeButton;

    private final ViewNavigator viewNavigator = new ViewNavigator();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindEvents();
        applyStyles();
    }

    private void bindEvents() {
        EventBinder.bindSettingsButton(settingsButton, () -> navigate(viewNavigator::openSettingsView));
        EventBinder.bindLevelCircle(levelOneButton, () -> navigate(viewNavigator::goToLevelOne));
        EventBinder.bindLevelCircle(levelTwoButton, () -> navigate(viewNavigator::goToLevelTwo));
        EventBinder.bindLevelCircle(levelThreeButton, () -> navigate(viewNavigator::goToLevelThree));
    }

    private void applyStyles() {
        ElementDecorator.decorateSettingsButton(settingsButton);

        Color circleColor = Color.RED;
        ElementDecorator.decorateLevelCircle(levelOneButton, circleColor);
        ElementDecorator.decorateLevelCircle(levelTwoButton, circleColor);
        ElementDecorator.decorateLevelCircle(levelThreeButton, circleColor);
    }

    private void navigate(NavigationAction action) {
        try {
            action.navigate();
        } catch (InvalidResourceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(String param) throws Exception {
        if (!Platform.isFxApplicationThread()) {
            ViewLegacy.javaFxFactory().mainAppManager().get().runOnAppThread(() -> safelyRunOnFXThread(param));
        } else {
            safelyRunOnFXThread(param);
        }
    }

    private void safelyRunOnFXThread(String param) {
        try {
            runOnFXThread(param);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private void runOnFXThread(String param) {
        System.out.println("[Main Menu Panel]");
    }
}
