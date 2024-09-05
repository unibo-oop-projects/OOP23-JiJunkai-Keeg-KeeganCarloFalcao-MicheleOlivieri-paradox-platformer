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
    private Button settingsButton; // Button to navigate to settings

    @FXML
    private Circle levelOneButton, levelTwoButton, levelThreeButton; // Circles representing different game levels

    private final ViewNavigator viewNavigator = new ViewNavigator(); // Handles navigation between views

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindEvents();  // Bind UI events to their respective handlers
        applyStyles(); // Apply styling to UI elements
    }

    private void bindEvents() {
        // Bind actions to UI elements using EventBinder
        EventBinder.bindSettingsButton(settingsButton, () -> navigate(viewNavigator::openSettingsView));
        EventBinder.bindLevelCircle(levelOneButton, () -> navigate(viewNavigator::goToLevelOne));
        EventBinder.bindLevelCircle(levelTwoButton, () -> navigate(viewNavigator::goToLevelTwo));
        EventBinder.bindLevelCircle(levelThreeButton, () -> navigate(viewNavigator::goToLevelThree));
    }

    private void applyStyles() {
        // Apply styles to the settings button and level circles
        ElementDecorator.decorateSettingsButton(settingsButton);
        Color circleColor = Color.RED; // Color for level circles
        ElementDecorator.decorateLevelCircle(levelOneButton, circleColor);
        ElementDecorator.decorateLevelCircle(levelTwoButton, circleColor);
        ElementDecorator.decorateLevelCircle(levelThreeButton, circleColor);
    }

    private void navigate(NavigationAction action) {
        try {
            action.navigate(); // Perform the navigation action
        } catch (InvalidResourceException e) {
            e.printStackTrace(); // Print error if navigation fails
        }
    }

    @Override
    public void create(String param) throws Exception {
        // Ensure that the code runs on the JavaFX application thread
        if (!Platform.isFxApplicationThread()) {
            ViewLegacy.javaFxFactory().mainAppManager().get().runOnAppThread(() -> safelyRunOnFXThread(param));
        } else {
            safelyRunOnFXThread(param);
        }
    }

    private void safelyRunOnFXThread(String param) {
        try {
            runOnFXThread(param); // Run the given code on the JavaFX thread
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e); // Wrap and rethrow exception
        }
    }

    private void runOnFXThread(String param) {
        System.out.println("[Main Menu Panel]"); // Debug output or placeholder for actual logic
    }
}
