package com.project.paradoxplatformer.view.fxcomponents.containers;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.fxcomponents.api.GraphicComponent;
import com.project.paradoxplatformer.view.fxcomponents.containers.api.GraphicContainer;
import com.project.paradoxplatformer.view.fxcomponents.keyinputs.KeyAssetterImpl;
import com.project.paradoxplatformer.view.fxcomponents.keyinputs.KeyTranslatorFX;
import com.project.paradoxplatformer.view.fxcomponents.keyinputs.api.FXKeyAssetter;
import com.project.paradoxplatformer.view.fxcomponents.keyinputs.api.InputType;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class GraphicContainerImpl implements GraphicContainer{

    private final StackPane uiContainer;
    private final FXKeyAssetter keyAssetter;
    private boolean isActive;

    public GraphicContainerImpl(StackPane container) {
        this.uiContainer = container;
        this.keyAssetter = new KeyAssetterImpl();
    }

    @Override
    public boolean render(GraphicComponent component) throws AssertionError{
        assert component instanceof Node;
        return uiContainer.getChildren().add((Node)component.unwrap());
    }

    @Override
    public Dimension dimension() {
        return new Dimension(this.uiContainer.getPrefWidth(), this.uiContainer.getPrefHeight());
    }

    @Override
    public void setDimension(double width, double height) {
        this.uiContainer.setPrefSize(width, height);
    }

    @Override
    public void setKeyPressed() {
        if(this.isActive) {
            this.uiContainer.addEventFilter(KeyEvent.KEY_PRESSED, this.keyAssetter::add);
        }
    }

    @Override
    public void setKeyReleased() {
        if(this.isActive) {
            this.uiContainer.addEventFilter(KeyEvent.KEY_RELEASED, this.keyAssetter::remove);
        }
    }

    @Override
    public FXKeyAssetter getKeyAssetter() {
        Objects.requireNonNull(this.keyAssetter);
        return this.keyAssetter;
    }

    @Override
    public void activateKeyInput() {
        this.uiContainer.requestFocus();
        this.isActive = true;
    }
    
}
