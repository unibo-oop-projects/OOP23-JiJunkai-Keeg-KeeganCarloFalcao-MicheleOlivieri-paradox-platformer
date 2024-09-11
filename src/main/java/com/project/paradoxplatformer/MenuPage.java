package com.project.paradoxplatformer;

import java.net.URL;
import java.util.ResourceBundle;

import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.view.EventBinder;
import com.project.paradoxplatformer.view.ViewNavigator;

import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MenuPage extends AbstractThreadedPage implements Initializable {

    protected static final double TRESHOLD_RATIO = 2.2d;

    @FXML
    private Button settingsButton; // Button to navigate to settings

    @FXML
    private ImageView circlesEffects;

    @FXML 
    private BorderPane pagePane;

    @FXML
    private Button levelOneButton, levelTwoButton, levelThreeButton; // Circles representing different game levels

    private final ViewNavigator viewNavigator = ViewNavigator.getInstance(); // Handles navigation between views
    Color circleColor = Color.RED; // Color for level circles

    private Transition animation;

    private double regWidtht, regHeight;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindEvents(); // Bind UI events to their respective handlers
        applyEffects();
    }

    private void applyEffects() {
        ColorAdjust colorAdj = new ColorAdjust();
        pagePane.setEffect(colorAdj);
        this.regWidtht = this.circlesEffects.getFitWidth();
        this.regHeight = this.circlesEffects.getFitHeight();

        animation = new Transition() {
            {
                setCycleDuration(Duration.millis(5000));
            }
        
            protected void interpolate(double frac) {
                System.out.println(frac);
                colorAdj.setContrast(frac / TRESHOLD_RATIO);
                circlesEffects.setFitHeight(frac * regHeight);
                circlesEffects.setFitWidth(frac * regWidtht);
            }
        
        };
    }

    private void bindEvents() {
        // Bind actions to UI elements using EventBinder
        EventBinder.bindButtons(settingsButton, () -> navigate(viewNavigator::openSettingsView));
        EventBinder.bindButtons(levelOneButton, () -> navigate(viewNavigator::goToLevelOne));
        EventBinder.bindButtons(levelTwoButton, () -> navigate(viewNavigator::goToLevelTwo));
        EventBinder.bindButtons(levelThreeButton, () -> navigate(viewNavigator::goToLevelThree));
    }

    private void navigate(NavigationAction action) {
        try {
            this.animation.stop();
            action.navigate(); // Perform the navigation action
        } catch (InvalidResourceException e) {
            e.printStackTrace(); // Print error if navigation fails
        }
    }

    @Override
    protected void runOnFXThread(String param) {
        this.animation.play();
        System.out.println("[Main Menu Panel]"); // Debug output or placeholder for actual logic
    }

    @Override
    public String toString() {
        return "New Level Controller";
    }
}
