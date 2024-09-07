package com.project.paradoxplatformer.view.javafx.fxcomponents;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.graphics.Actionable;
import com.project.paradoxplatformer.view.javafx.fxcomponents.abstracts.AbstractFXGraphicAdapter;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public final class FXButtonAdapter extends AbstractFXGraphicAdapter implements Actionable{

    private final Button buttonCompo;

    public FXButtonAdapter(Dimension dimension, Coord2D relativePos, String text) {
        super(new Button(), dimension, relativePos);
        if (this.uiComponent instanceof Button buttonCopy) {
            this.buttonCompo = buttonCopy;
            this.buttonCompo.setText(text);
        } else {
            throw new IllegalArgumentException("Require button javafx class");
        }
    }

    public FXButtonAdapter(Dimension dimension, Coord2D relativePos, String text, Runnable action) {
        this(dimension, relativePos, text);
        this.onAction(action);
    }

    public FXButtonAdapter(final String text) {
        this(Dimension.dot(), Coord2D.origin(), text);
    }

    @Override
    public void setDimension(double width, double height) {
        System.out.println(width);
        this.buttonCompo.setPrefHeight(height);
        this.buttonCompo.setPrefWidth(width);
    }

    public Optional<Color> color() {
        return Optional.of(this.buttonCompo.getTextFill())
            .filter(Color.class::isInstance)
            .map(Color.class::cast);
    }

    @Override
    public void onAction(Runnable action) {
        this.buttonCompo.setOnAction(e -> action.run());
    }
    
}
