package com.project.paradoxplatformer;

import java.net.URL;
import java.util.ResourceBundle;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.view.EventBinder;
import com.project.paradoxplatformer.view.ViewNavigator;

import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Controller for the menu page of the game.
 */
public class MenuPage extends AbstractThreadedPage {

    // Ratio for adjusting color contrast during animation
    private static final double TRESHOLD_RATIO = 2.2d;

    @FXML
    private Button settingsButton; // Button to navigate to settings

    @FXML
    private ImageView circlesEffects; // Image view for circle effects

    @FXML
    private BorderPane pagePane; // Main layout pane

    @FXML
    private Button levelOneButton, levelTwoButton, levelThreeButton, levelFourButton; // Buttons for different game
                                                                                      // levels

    private final ViewNavigator viewNavigator = ViewNavigator.getInstance(); // Handles view navigation
    private Color circleColor = Color.RED; // Color for level circles

    private Transition animation; // Transition for animating circle effects
    private double regWidtht, regHeight; // Original width and height of the circles image

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindEvents(); // Bind UI elements to their event handlers
        applyEffects(); // Set up visual effects
    }

    /**
     * Sets up visual effects for the menu page.
     */
    private void applyEffects() {
        ColorAdjust colorAdj = new ColorAdjust();
        pagePane.setEffect(colorAdj); // Apply color adjustment effect to the page pane

        // Store the original dimensions of the circles image
        regWidtht = circlesEffects.getFitWidth();
        regHeight = circlesEffects.getFitHeight();

        // Define the animation for the circles image
        animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000)); // Set duration of the animation
            }

            @Override
            protected void interpolate(double frac) {
                colorAdj.setContrast(frac / TRESHOLD_RATIO); // Adjust contrast based on animation progress
                circlesEffects.setFitHeight(frac * regHeight); // Scale the height of the circles image
                circlesEffects.setFitWidth(frac * regWidtht); // Scale the width of the circles image
            }
        };
    }

    /**
     * Binds event handlers to UI elements using the EventBinder class.
     */
    private void bindEvents() {
        EventBinder.bindButtons(settingsButton, this::navigateToSettings);
        EventBinder.bindButtons(levelOneButton, this::navigateToLevelOne);
        EventBinder.bindButtons(levelTwoButton, this::navigateToLevelTwo);
        EventBinder.bindButtons(levelThreeButton, this::navigateToLevelThree);
        EventBinder.bindButtons(levelFourButton, this::navigateToLevelFour);
    }

    /**
     * Navigates to the settings view.
     */
    private void navigateToSettings() {
        navigate(viewNavigator::openSettingsView);
    }

    /**
     * Navigates to Level One.
     */
    private void navigateToLevelOne() {
        navigate(viewNavigator::goToLevelOne);
    }

    /**
     * Navigates to Level Two.
     */
    private void navigateToLevelTwo() {
        navigate(viewNavigator::goToLevelTwo);
    }

    /**
     * Navigates to Level Three.
     */
    private void navigateToLevelThree() {
        navigate(viewNavigator::goToLevelThree);
    }

    /**
     * Navigates to Level Four.
     */
    private void navigateToLevelFour() {
        navigate(viewNavigator::goToLevelFour);
    }

    /**
     * Performs navigation and handles potential exceptions.
     * 
     * @param action The navigation action to perform.
     */
    private void navigate(NavigationAction action) {
        try {
            animation.stop(); // Stop the animation before navigating
            action.navigate(); // Perform the navigation action
        } catch (InvalidResourceException e) {
            e.printStackTrace(); // Print stack trace if an exception occurs
        }
    }

    @Override
    protected void runOnFXThread(String param) {
        animation.play(); // Start the animation
        System.out.println("[Main Menu Panel]"); // Debug output or placeholder for actual logic
    }

    @Override
    public String toString() {
        return "Menu Page Controller"; // Return a descriptive name for the controller
    }
}
