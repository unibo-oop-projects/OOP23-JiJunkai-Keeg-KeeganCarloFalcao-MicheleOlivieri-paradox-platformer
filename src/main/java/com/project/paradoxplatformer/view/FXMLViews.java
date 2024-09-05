package com.project.paradoxplatformer.view;

public enum FXMLViews {
    SETTINGS("SettingsView.fxml"),
    LEVEL_ONE("hello-view.fxml"),
    LEVEL_TWO("LevelTwo.fxml"),
    LEVEL_THREE("LevelThree.fxml");

    private final String fileName;

    FXMLViews(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
