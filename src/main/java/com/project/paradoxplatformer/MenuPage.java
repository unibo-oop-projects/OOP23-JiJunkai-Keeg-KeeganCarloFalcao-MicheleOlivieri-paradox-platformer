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
import javafx.util.Duration;

/**
 * Controller for the game's main menu page.
 * This class manages the menu UI, handles navigation between game levels,
 * and applies visual effects to the menu.
 */
public class MenuPage extends AbstractThreadedPage {

    // A ratio used for adjusting the contrast during the transition effect.
    private static final double TRESHOLD_RATIO = 2.2d;

    // UI elements from the FXML file
    @FXML
    private Button settingsButton; // Button that navigates to the settings view

    @FXML
    private ImageView circlesEffects; // Image view used to display circle effects on the page

    @FXML
    private BorderPane pagePane; // Main layout container for the menu page

    @FXML
    private Button levelOneButton, levelTwoButton, levelThreeButton, levelFourButton;
    // Buttons to select different levels (Level 1, 2, 3, 4) for the game

    // Responsible for navigating between different views in the application
    private final ViewNavigator viewNavigator = ViewNavigator.getInstance();

    // Animation that applies a visual transition effect to the menu page
    private Transition animation;
    private double regWidtht, regHeight; // Stores the original width and height of the `circlesEffects` ImageView

    /**
     * Initializes the controller class and is called automatically after the FXML
     * file has been loaded.
     * Sets up event bindings and applies visual effects.
     *
     * @param location  The location used to resolve relative paths for the root
     *                  object.
     * @param resources The resources used to localize the root object.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindEvents(); // Bind UI components to their event handlers
        applyEffects(); // Apply visual effects to the UI components
    }

    /**
     * Applies visual effects such as color adjustment and scaling animations to the
     * menu page.
     * This includes a smooth transition where the contrast and size of the circles
     * image changes.
     */
    private void applyEffects() {
        // Creates a color adjustment effect (used for contrast changes)
        ColorAdjust colorAdj = new ColorAdjust();
        pagePane.setEffect(colorAdj); // Apply the color adjustment to the entire page

        // Store the initial size of the `circlesEffects` ImageView
        regWidtht = circlesEffects.getFitWidth();
        regHeight = circlesEffects.getFitHeight();

        // Define an animation that smoothly changes the contrast and size of the
        // circles
        animation = new Transition() {
            {
                // The duration of the animation is set to 2000 milliseconds (2 seconds)
                setCycleDuration(Duration.millis(2000));
            }

            @Override
            protected void interpolate(double frac) {
                // Interpolate the contrast effect proportionally to the animation progress
                // (frac)
                colorAdj.setContrast(frac / TRESHOLD_RATIO);

                // Adjust the size of the `circlesEffects` based on the progress of the
                // animation
                circlesEffects.setFitHeight(frac * regHeight);
                circlesEffects.setFitWidth(frac * regWidtht);
            }
        };
    }

    /**
     * Binds event handlers to the UI buttons.
     * This method uses the `EventBinder` utility to link button actions to specific
     * navigation methods.
     */
    private void bindEvents() {
        // Binds the settings button to navigate to the settings view
        EventBinder.bindButtons(settingsButton, this::navigateToSettings);

        // Binds the level buttons to navigate to their respective levels
        EventBinder.bindButtons(levelOneButton, this::navigateToLevelOne);
        EventBinder.bindButtons(levelTwoButton, this::navigateToLevelTwo);
        EventBinder.bindButtons(levelThreeButton, this::navigateToLevelThree);
        EventBinder.bindButtons(levelFourButton, this::navigateToLevelFour);
    }

    /**
     * Navigates to the settings view when the settings button is clicked.
     */
    private void navigateToSettings() {
        navigate(viewNavigator::openSettingsView); // Perform the navigation using the ViewNavigator
    }

    /**
     * Navigates to Level One when the corresponding button is clicked.
     */
    private void navigateToLevelOne() {
        navigate(viewNavigator::goToLevelOne); // Perform the navigation to Level One
    }

    /**
     * Navigates to Level Two when the corresponding button is clicked.
     */
    private void navigateToLevelTwo() {
        navigate(viewNavigator::goToLevelTwo); // Perform the navigation to Level Two
    }

    /**
     * Navigates to Level Three when the corresponding button is clicked.
     */
    private void navigateToLevelThree() {
        navigate(viewNavigator::goToLevelThree); // Perform the navigation to Level Three
    }

    /**
     * Navigates to Level Four when the corresponding button is clicked.
     */
    private void navigateToLevelFour() {
        navigate(viewNavigator::goToLevelFour); // Perform the navigation to Level Four
    }

    /**
     * Generalized method to handle navigation between views.
     * Ensures that the current animation is stopped before navigation occurs.
     * 
     * @param action The navigation action to be executed.
     */
    private void navigate(NavigationAction action) {
        try {
            animation.stop(); // Stop the current animation before navigating
            action.navigate(); // Execute the navigation action (e.g., change views)
        } catch (InvalidResourceException e) {
            e.printStackTrace(); // Print error stack trace if navigation fails due to an invalid resource
        }
    }

    /**
     * This method is run on the JavaFX Application Thread and is responsible for
     * starting
     * the visual effects (animation) when needed.
     *
     * @param param Unused parameter, could be used for passing extra data in the
     *              future.
     */
    @Override
    protected void runOnFXThread(String param) {
        animation.play(); // Start playing the animation
        System.out.println("[Main Menu Panel]"); // Log a message for debugging purposes
    }

    /**
     * Provides a string representation of the controller.
     * This is useful for logging and debugging.
     *
     * @return A string describing the controller's purpose.
     */
    @Override
    public String toString() {
        return "Menu Page Controller"; // A simple description of this class
    }
}
