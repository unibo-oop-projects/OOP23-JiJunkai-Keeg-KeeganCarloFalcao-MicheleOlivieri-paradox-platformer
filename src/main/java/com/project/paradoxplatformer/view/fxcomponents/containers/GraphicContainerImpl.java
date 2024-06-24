package com.project.paradoxplatformer.view.fxcomponents.containers;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.fxcomponents.GraphicComponent;

import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class GraphicContainerImpl implements GraphicContainer{

    private final StackPane uiContainer;

    public GraphicContainerImpl(StackPane container) {
        this.uiContainer = container;
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
    
}
