package com.project.paradoxplatformer.view.page;

import java.net.URL;
import java.util.ResourceBundle;

import com.project.paradoxplatformer.controller.games.Level;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * Controller for the app settings page.
 * This controller handles and changes view-based settings such as brightness,
 * sound, and resolution.
 */
public final class SettingsPage extends AbstractThreadedPage {

    @FXML
    private Button toggleSoundButton;

    @FXML
    private Button saveButton;

    @FXML
    private CheckBox resolution720p;

    @FXML
    private CheckBox resolution1080p;

    @FXML
    private CheckBox resolution1440p;

    @FXML
    private CheckBox resolution4k;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        bindEvents();
    }

    private void bindEvents() {
        saveButton.setOnAction(e -> saveSettings());
    }

    private void saveSettings() {
        // Handle selected resolution
        if (resolution720p.isSelected()) {
            System.out.println("Resolution set to 1280x720");
        } else if (resolution1080p.isSelected()) {
            System.out.println("Resolution set to 1920x1080");
        } else if (resolution1440p.isSelected()) {
            System.out.println("Resolution set to 2560x1440");
        } else if (resolution4k.isSelected()) {
            System.out.println("Resolution set to 3840x2160");
        } else {
            System.out.println("No resolution selected");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void runOnFXThread(final Level param) throws Exception {
        // Placeholder for actual logic
        System.out.println("[Main Settings Panel]");
    }

}
