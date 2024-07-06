package com.project.paradoxplatformer.model.world.mappings.view;

import com.project.paradoxplatformer.model.world.mappings.EntityDataMapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.FXImageComponent;
import com.project.paradoxplatformer.view.fxcomponents.FXRectangleComponent;
import com.project.paradoxplatformer.view.graphics.GraphicComponent;

public class ViewMappingFactoryImpl implements ViewMappingFactory{

    public ViewMappingFactoryImpl() {}

    @Override
    public EntityDataMapper<GraphicComponent> imageToView() {
        return g -> new FXImageComponent(
                new Dimension(g.getWidth(), g.getHeight()),
                new Coord2D(g.getX(), g.getY()),
                g.getImage()
            );
    }

    @Override
    public EntityDataMapper<GraphicComponent> blockToView() {
        return g -> new FXRectangleComponent(
                new Dimension(g.getWidth(), g.getHeight()),
                new Coord2D(g.getX(), g.getY()),
                g.getColor().toFXColor()
        );
    }
}
