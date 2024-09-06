package com.project.paradoxplatformer.view.swing;

import javax.swing.JComponent;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

import javafx.beans.value.ObservableDoubleValue;

public abstract class AbstractSwingAdapter implements GraphicAdapter<JComponent>{


    protected JComponent uiComponent;
    private Dimension dimension;
    private Coord2D coord;
    private int key;

    protected AbstractSwingAdapter(final JComponent component, Dimension dimension, Coord2D relativePos) {
        this.uiComponent = component;   
        this.dimension = dimension;
        this.coord = relativePos;
    }
    @Override
    public JComponent unwrap() {
        return uiComponent;
    }

    @Override
    public Dimension dimension() {
        return this.dimension;
    }

    @Override
    public Coord2D absolutePosition() {
        return new Coord2D(this.uiComponent.getX(), this.uiComponent.getY());
    }

    @Override
    public Coord2D relativePosition() {
        return this.coord;
    }

    @Override
    public void setDimension(double width, double height) {
        this.uiComponent.setSize((int) width, (int) height);
    }

    @Override
    public void setPosition(double x, double y) {
        // uiComponent.getGraphics().translate((int) x, (int)y);
        this.uiComponent.setLocation((int) x, (int)y);
    }

    @Override
    public void translate(double x, double y) {
        this.uiComponent.setLocation((int) x, (int)y);
    }

    @Override
    public void bindPropreties(ObservableDoubleValue wRatio, ObservableDoubleValue hRatio) {
        /*Unboundable */
    }

    @Override
    public abstract void flip();
    @Override
    public void setKey(int key) {
        this.key = key;
    }
    @Override
    public int getKey() {
        return this.key;
    }
    
    
}
