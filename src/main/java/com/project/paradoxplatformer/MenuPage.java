package com.project.paradoxplatformer;

import java.net.URL;
import java.util.ResourceBundle;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.view.ElementDecorator;
import com.project.paradoxplatformer.view.EventBinder;
import com.project.paradoxplatformer.view.ViewNavigator;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MenuPage extends AbstractThreadedPage implements Initializable {

    @FXML
    private Button settingsButton; // Button to navigate to settings

    @FXML
    private Circle levelOneButton, levelTwoButton, levelThreeButton; // Circles representing different game levels

    private final ViewNavigator viewNavigator = ViewNavigator.getInstance(); // Handles navigation between views
    Color circleColor = Color.RED; // Color for level circles

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindEvents(); // Bind UI events to their respective handlers
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
    protected void runOnFXThread(String param) {
        System.out.println("[Main Menu Panel]"); // Debug output or placeholder for actual logic
    }

    @Override
    public String toString() {
        return "New Level Controller";
    }
}
