package com.project.paradoxplatformer.view.javafx.fxcomponents;

import java.util.Objects;
import java.util.function.Function;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.model.MenuItem;
import com.project.paradoxplatformer.model.mappings.EntityDataMapper;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.game.ViewMappingFactory;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

import javafx.scene.Node;

public class FXViewMappingFactoryImpl implements ViewMappingFactory<Node> {

    private static final int DEFAULT_ID = 0;

    public FXViewMappingFactoryImpl() {}

    @Override         
    public EntityDataMapper<GraphicAdapter<Node>> imageToView(){
        return this::reckonImageFromSprite;
    }

    @Override
    public EntityDataMapper<GraphicAdapter<Node>> blockToView() {
        return g -> new FXRectangleAdapter(
            g.getID(),
            new Dimension(g.getWidth(), g.getHeight()),
            new Coord2D(g.getX(), g.getY()),
            g.getColor().toFXColor()
        );
    }

    private GraphicAdapter<Node> reckonImageFromSprite(GameDTO g) {
        try {
            return Objects.nonNull(g.getSpriteMeta()) ? new FXSpriteAdapter(
                    g.getID(),
                    new Dimension(g.getWidth(), g.getHeight()),
                    new Coord2D(g.getX(), g.getY()),
                    g.getImage(), 
                    g.getSpriteMeta()
                ) :
                new FXImageAdapter(
                    g.getID(),
                    new Dimension(g.getWidth(), g.getHeight()),
                    new Coord2D(g.getX(), g.getY()),
                    g.getImage()
                ); 
        } catch (InvalidResourceException e) {
            throw new IllegalStateException(e);
        }
        
    }

    @Override
    public Function<MenuItem, GraphicAdapter<Node>> menuItemToView(final GameController<Node> gameController) {
        return m -> {
            var button = new FXButtonAdapter(DEFAULT_ID, m.name());
            button.onAction(m.action(), gameController);
            return button;
        };
    }
}
