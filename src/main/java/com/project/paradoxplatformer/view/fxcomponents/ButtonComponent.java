package com.project.paradoxplatformer.view.fxcomponents;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.abstracts.AbstractGraphicComponent;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ButtonComponent extends AbstractGraphicComponent implements Actionable{

    private final Button buttonCompo;

    public ButtonComponent(Dimension dimension, Coord2D relativePos, String text) {
        super(new Button(), dimension, relativePos);
        if (this.uiComponent instanceof Button buttonCopy) {
            this.buttonCompo = buttonCopy;
            this.buttonCompo.setText(text);
            
        } else {
            throw new IllegalArgumentException("Require button javafx class");
        }
    }

    @Override
    public void setDimension(double width, double height) {
        System.out.println(width);
        this.buttonCompo.setPrefHeight(height);
        this.buttonCompo.setPrefWidth(width);
    }

    @Override
    public Node unwrap() {
        return this.buttonCompo;
    }

    @Override
    public Optional<Image> image() {
        return Optional.empty();
    }

    @Override
    public Optional<Color> color() {
        return Optional.of((Color)this.buttonCompo.getTextFill());
    }

    @Override
    public void onAction(Runnable action) {
        this.buttonCompo.setOnAction(e -> action.run());
    }
    
}
