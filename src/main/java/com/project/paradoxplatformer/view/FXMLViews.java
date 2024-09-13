package com.project.paradoxplatformer.view;

/**
 * Enum representing different FXML views in the application.
 * Each enum constant corresponds to an FXML file used for UI views.
 */
public enum FXMLViews {
    MENU("menu-view.fxml"), // Represents the FXML file for the main menu view
    SETTINGS("setting-view.fxml"), // Represents the FXML file for the settings view
    GAME("game-view.fxml"); // Represents the FXML file for the game view

    private final String fileName; // The filename of the FXML view

    /**
     * Constructs an FXMLViews enum constant with the specified FXML file name.
     *
     * @param fileName The name of the FXML file associated with the view.
     */
    FXMLViews(String fileName) {
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
