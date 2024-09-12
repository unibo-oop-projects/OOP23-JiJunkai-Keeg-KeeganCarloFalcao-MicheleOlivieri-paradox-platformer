package com.project.paradoxplatformer.view.fxmlstyle;

import javafx.scene.control.Button;

public class ButtonStyleBuilder {
    private final Button button;

    public ButtonStyleBuilder(Button button) {
        this.button = button;
    }

    public ButtonStyleBuilder withBackgroundColor(String color) {
        button.setStyle("-fx-background-color: " + color + ";");
        return this;
    }

    public ButtonStyleBuilder withTextColor(String color) {
        button.setStyle(button.getStyle() + " -fx-text-fill: " + color + ";");
        return this;
    }

    public ButtonStyleBuilder withFontWeight(String weight) {
        button.setStyle(button.getStyle() + " -fx-font-weight: " + weight + ";");
        return this;
    }

    public void apply() {
        
    }
}
