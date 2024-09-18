package com.project.paradoxplatformer.view.manager.api;

import com.project.paradoxplatformer.utils.ResourcesFinder;

/**
 * Enum representing different FXML views in the application.
 * Each enum constant corresponds to an FXML file used for UI views.
 */
public enum FXMLView {
    /**
     * Represents the FXML file for the main menu view.
     */
    MENU(ResourcesFinder.FXML_FOLDER + "menu-view.fxml"),

    /**
     * Represents the FXML file for the settings view.
     */
    SETTINGS(ResourcesFinder.FXML_FOLDER + "setting-view.fxml"),

    /**
     * Represents the FXML file for the game view.
     */
    GAME(ResourcesFinder.FXML_FOLDER + "game-view.fxml"),

    /**
     * Error window.
     */
    ERROR_DIAG(ResourcesFinder.FXML_FOLDER + "diag-pane.fxml");

    private final String fileName; // The filename of the FXML view

    /**
     * Constructs an FXMLViews enum constant with the specified FXML file name.
     *
     * @param fileName The name of the FXML file associated with the view. This
     *                 parameter should be final.
     */
    FXMLView(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the filename of the FXML view.
     *
     * @return The filename of the FXML view.
     */
    public String getFileName() {
        return fileName;
    }
}
