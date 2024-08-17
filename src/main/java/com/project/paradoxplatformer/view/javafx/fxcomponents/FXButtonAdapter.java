package com.project.paradoxplatformer.view.javafx.fxcomponents;

import java.util.Optional;

import com.project.paradoxplatformer.utils.SecureWrapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.graphics.Actionable;
import com.project.paradoxplatformer.view.javafx.fxcomponents.abstracts.AbstractFXGraphicAdapter;

import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public final class FXButtonAdapter extends AbstractFXGraphicAdapter implements Actionable{

    private final SecureWrapper<Button> buttonCompo;

    public FXButtonAdapter(Dimension dimension, Coord2D relativePos, String text) {
        super(new Button(), dimension, relativePos);
        if (this.uiComponent instanceof Button buttonCopy) {
            this.buttonCompo = SecureWrapper.of(buttonCopy);
            this.buttonCompo.get().setText(text);
        } else {
            throw new IllegalArgumentException("Require button javafx class");
        }
    }

    public FXButtonAdapter(final String text) {
        this(Dimension.dot(), Coord2D.origin(), text);
    }

    @Override
    public void setDimension(double width, double height) {
        System.out.println(width);
        this.buttonCompo.get().setPrefHeight(height);
        this.buttonCompo.get().setPrefWidth(width);
    }

    public Optional<Color> color() {
        return Optional.of(this.buttonCompo.get().getTextFill())
            .filter(Color.class::isInstance)
            .map(Color.class::cast);
    }

    @Override
    public void onAction(Runnable action) {
        this.buttonCompo.get().setOnAction(e -> action.run());
    }
    
    @Override
    public void bindPropreties(ObservableDoubleValue wratio, ObservableDoubleValue hratio) {
        
    }
}
