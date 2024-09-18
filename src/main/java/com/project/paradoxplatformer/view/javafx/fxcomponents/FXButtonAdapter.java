package com.project.paradoxplatformer.view.javafx.fxcomponents;

import java.util.Optional;

import com.project.paradoxplatformer.model.inputmodel.commands.Command;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.graphics.Actionable;
import com.project.paradoxplatformer.view.javafx.fxcomponents.abstracts.AbstractFXGraphicAdapter;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * A JavaFX-based graphical adapter for buttons, extending the abstract graphic
 * adapter
 * and implementing the {@link Actionable} interface for handling actions.
 */
public final class FXButtonAdapter extends AbstractFXGraphicAdapter implements Actionable {

    private final Button buttonCompo;

    /**
     * Constructs an FXButtonAdapter with specified dimensions, position, and text.
     * 
     * @param id the unique id of the button
     * @param dimension   the dimensions of the button
     * @param relativePos the position of the button relative to its container
     * @param text        the text to display on the button
     * @throws IllegalArgumentException if the underlying UI component is not a
     *                                  {@link Button}
     */
    public FXButtonAdapter(final int id, final Dimension dimension, final Coord2D relativePos, final String text) {
        super(id, new Button(), dimension, relativePos);
        if (super.getUiComponent() instanceof Button buttonCopy) {
            this.buttonCompo = buttonCopy;
            this.buttonCompo.setText(text);
        } else {
            throw new IllegalArgumentException("Require button javafx class");
        }
    }

    /**
     * Constructs an FXButtonAdapter with default id, dimensions and position, and
     * specified text.
     * 
     * @param id the unique id of the button
     * 
     * @param text the text to display on the button
     */
    public FXButtonAdapter(final int id, final String text) {
        this(id, Dimension.dot(), Coord2D.origin(), text);
    }

    @Override
    public void setDimension(final double width, final double height) {
//        System.out.println(width);
        this.buttonCompo.setPrefHeight(height);
        this.buttonCompo.setPrefWidth(width);
    }

    /**
     * Gets the color of the text on the button.
     * 
     * @return an {@link Optional} containing the text color if it is an instance of
     *         {@link Color}, otherwise an empty {@link Optional}
     */
    public Optional<Color> color() {
        return Optional.of(this.buttonCompo.getTextFill())
                .filter(Color.class::isInstance)
                .map(Color.class::cast);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <G> void onAction(final Command<G> action, final G actor) {
        this.buttonCompo.setOnAction(e -> action.execute(actor));
    }
}
