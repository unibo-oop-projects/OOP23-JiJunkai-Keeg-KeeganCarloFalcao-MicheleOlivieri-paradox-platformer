package com.project.paradoxplatformer.view.fxmlstyle.builder;

import javafx.scene.control.Button;

/**
 * A builder class for applying various styles to a {@link Button}.
 * This class follows the Builder design pattern to allow for fluent
 * and incremental style configuration.
 */
public class ButtonStyleBuilder extends AbstractStyleBuilder<Button> {

    /**
     * Constructs a new ButtonStyleBuilder for the given button.
     * 
     * @param button The button to style.
     */
    public ButtonStyleBuilder(Button button) {
        super(button);
    }

    /**
     * Sets the background color of the button.
     * 
     * @param color The color to set as the background, in CSS format.
     * @return This ButtonStyleBuilder instance for chaining.
     */
    public ButtonStyleBuilder withBackgroundColor(String color) {
        node.setStyle("-fx-background-color: " + color + ";");
        return this;
    }

    /**
     * Sets the text color of the button.
     * 
     * @param color The color to set as the text color, in CSS format.
     * @return This ButtonStyleBuilder instance for chaining.
     */
    public ButtonStyleBuilder withTextColor(String color) {
        node.setStyle(node.getStyle() + " -fx-text-fill: " + color + ";");
        return this;
    }

    /**
     * Sets the font weight of the button's text.
     * 
     * @param weight The font weight to apply (e.g., "bold").
     * @return This ButtonStyleBuilder instance for chaining.
     */
    public ButtonStyleBuilder withFontWeight(String weight) {
        node.setStyle(node.getStyle() + " -fx-font-weight: " + weight + ";");
        return this;
    }

    @Override
    public void apply() {
        // Apply method is empty as styles are applied incrementally.
    }
}
