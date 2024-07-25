package com.project.paradoxplatformer.view.javafx.fxcomponents;

import com.project.paradoxplatformer.model.mappings.EntityDataMapper;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.game.ViewMappingFactory;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

import javafx.scene.Node;

public class FXViewMappingFactoryImpl implements ViewMappingFactory<Node>{

    public FXViewMappingFactoryImpl() {}

    @Override         
    public EntityDataMapper<GraphicAdapter<Node>> imageToView(){
        return g ->  {
            try {
                return new FXImageAdapter(
                        new Dimension(g.getWidth(), g.getHeight()),
                        new Coord2D(g.getX(), g.getY()),
                        g.getImage()
                    );
            } catch (InvalidResourceException e) {
                throw new IllegalStateException(e);
            }
        };     
    }

    @Override
    public EntityDataMapper<GraphicAdapter<Node>> blockToView() {
        return g -> new FXRectangleAdapter(
            new Dimension(g.getWidth(), g.getHeight()),
            new Coord2D(g.getX(), g.getY()),
            g.getColor().toFXColor()
        );
    }
}
