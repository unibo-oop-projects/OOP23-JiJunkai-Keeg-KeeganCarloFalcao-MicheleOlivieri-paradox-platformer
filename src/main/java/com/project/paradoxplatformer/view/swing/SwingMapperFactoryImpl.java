package com.project.paradoxplatformer.view.swing;

import javax.swing.JComponent;

import com.project.paradoxplatformer.model.mappings.EntityDataMapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.game.ViewMappingFactory;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

public class SwingMapperFactoryImpl implements ViewMappingFactory<JComponent>{

    @Override
    public EntityDataMapper<GraphicAdapter<JComponent>> imageToView() {
        return g -> new SwingImageAdapter(
            new Dimension(g.getWidth(), g.getHeight()),
            new Coord2D(g.getX(), g.getY()),
            g.getImage()
        );
    }

    @Override
    public EntityDataMapper<GraphicAdapter<JComponent>> blockToView() {
        return g -> new SwingRectangleAdapter(
            new Dimension(g.getWidth(), g.getHeight()),
            new Coord2D(g.getX(), g.getY()),
            g.getColor().toAwtColor()
        );
    }
    
}
