package com.project.paradoxplatformer.model.world.mappings.view;

import java.util.function.Function;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.model.world.mappings.EntityDataMapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.ImageComponent;
import com.project.paradoxplatformer.view.fxcomponents.RectangleComponent;
import com.project.paradoxplatformer.view.fxcomponents.api.GraphicComponent;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ViewMappingFactoryImpl implements ViewMappingFactory{

    public ViewMappingFactoryImpl() {}

    @Override
    public EntityDataMapper<GraphicComponent> imageToView() {
        return g -> new ImageComponent(
                new ImageView(),
                new Dimension(g.getWidth(), g.getHeight()),
                new Coord2D(g.getX(), g.getY()),
                g.getImage()
            );
    }

    @Override
    public EntityDataMapper<GraphicComponent> blockToView() {
        return g -> new RectangleComponent(
                new Rectangle(),
                new Dimension(g.getWidth(), g.getHeight()),
                new Coord2D(g.getX(), g.getY()),
                g.getColor().toFXColor()
        );
    }
}
