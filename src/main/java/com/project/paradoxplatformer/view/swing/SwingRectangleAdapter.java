package com.project.paradoxplatformer.view.swing;

import javax.swing.JButton;
import java.awt.Color;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

public class SwingRectangleAdapter extends AbstractSwingAdapter {

    private JButton blockComponent;

    protected SwingRectangleAdapter(Dimension dimension, Coord2D position, Color fill) {
        super(new JButton(), dimension, position);
        if (this.uiComponent instanceof JButton blockCopy) {
            this.blockComponent = blockCopy;
            this.blockComponent.setBackground(fill);
            this.setDimension(dimension.width(), dimension.height());
        } else {
            throw new IllegalArgumentException("Require rectangle");
        }
    }

    @Override
    public void flip() {
        //Unflippable
    }

}
