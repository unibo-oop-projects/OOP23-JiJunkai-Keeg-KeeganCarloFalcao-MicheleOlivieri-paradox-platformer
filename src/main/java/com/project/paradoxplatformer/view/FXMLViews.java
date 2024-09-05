package com.project.paradoxplatformer.view;

public enum FXMLViews {
    MENU("menu-view.fxml"),
    SETTINGS("setting-view.fxml"),
    GAME("game-view.fxml");

    private final String fileName;

    FXMLViews(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
