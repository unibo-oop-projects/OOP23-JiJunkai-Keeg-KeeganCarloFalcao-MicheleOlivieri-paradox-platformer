package com.project.paradoxplatformer.model.world;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.ImageComponent;
import com.project.paradoxplatformer.view.fxcomponents.RectangleComponent;
import com.project.paradoxplatformer.view.fxcomponents.api.GraphicComponent;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.function.Function;

public class DataViewMapper {
    
        public static Function<GameDTO, GraphicComponent> imageToView() {
        return g -> new ImageComponent(
                new ImageView(),
                new Dimension(g.getWidth(), g.getHeight()),
                new Coord2D(g.getX(), g.getY()),
                g.getImage()
            );
    }

    public static Function<GameDTO, GraphicComponent> obstacleToModel() {
        return g -> new RectangleComponent(
                new Rectangle(),
                new Dimension(g.getWidth(), g.getHeight()),
                new Coord2D(g.getX(), g.getY()),
                g.getColor().toFXColor()
            );
    }

}
