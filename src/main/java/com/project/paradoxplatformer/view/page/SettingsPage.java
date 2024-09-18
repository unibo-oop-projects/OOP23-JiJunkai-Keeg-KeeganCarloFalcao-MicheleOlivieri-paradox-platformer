package com.project.paradoxplatformer.view.page;

import java.net.URL;
import java.util.ResourceBundle;

import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.view.renders.EventBinder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller for the app settings page.
 * This controller handles and changes view-based settings such as brightness,
 * sound, and resolution, and includes a quit button to exit the application.
 */
public final class SettingsPage extends AbstractThreadedPage {

    @FXML
    private Button quitButton;

    /**
     * Initializes the settings page.
     * Binds events to UI elements such as the quit button.
     *
     * @param location  The location used to resolve relative paths for the root
     *                  object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if
     *                  the resources are not available.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        EventBinder.bindButtons(quitButton, () -> Runtime.getRuntime().exit(0));
    }

    /**
     * Runs additional logic on the JavaFX thread, if necessary.
     * This method is called by the main application loop.
     *
     * @param param A parameter representing the current level, which can be used to
     *              run level-specific settings.
     * @throws Exception If any error occurs during execution.
     */
    @Override
    protected void runOnFXThread(final Level param) throws Exception {
        // System.out.println("[Main Settings Panel]");
    }
}
