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

/**
 * An implementation of the {@link ViewMappingFactory} interface for mapping
 * game entities to JavaFX graphical components.
 * This factory is responsible for creating graphical representations of game
 * entities, including images, blocks, and menu items.
 */
public class FXViewMappingFactoryImpl implements ViewMappingFactory<Node> {

    private static final int DEFAULT_ID = 0;

    /**
     * Constructs an {@link FXViewMappingFactoryImpl}.
     */
    public FXViewMappingFactoryImpl() {
    }

    /**
     * Maps game entities of type image to their corresponding
     * {@link GraphicAdapter}.
     * 
     * @return an {@link EntityDataMapper} that maps images to
     *         {@link GraphicAdapter<Node>} instances
     */
    @Override
    public EntityDataMapper<GraphicAdapter<Node>> imageToView() {
        return this::reckonImageFromSprite;
    }

    /**
     * Maps game entities of type block to their corresponding
     * {@link GraphicAdapter}.
     * 
     * @return an {@link EntityDataMapper} that maps blocks to
     *         {@link FXRectangleAdapter} instances
     */
    @Override
    public EntityDataMapper<GraphicAdapter<Node>> blockToView() {
        return g -> new FXRectangleAdapter(
            g.getID(),
            new Dimension(g.getWidth(), g.getHeight()),
            new Coord2D(g.getX(), g.getY()),
            g.getColor().toFXColor()
        );
    }

    /**
     * Creates a {@link GraphicAdapter} from a {@link GameDTO} object representing a
     * sprite or image.
     * 
     * @param g the {@link GameDTO} containing sprite or image data
     * @return a {@link GraphicAdapter<Node>} corresponding to the provided
     *         {@link GameDTO}
     * @throws IllegalStateException if the resource specified by {@link GameDTO} is
     *                               invalid
     */
    private GraphicAdapter<Node> reckonImageFromSprite(final GameDTO g) {
        try {
            return Objects.nonNull(g.getSpriteMeta()) ? new FXSpriteAdapter(
                    g.getID(),
                    new Dimension(g.getWidth(), g.getHeight()),
                    new Coord2D(g.getX(), g.getY()),
                    g.getImage(), 
                    g.getSpriteMeta()
                ) 
                :
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

    /**
     * Maps {@link MenuItem} objects to their corresponding {@link GraphicAdapter}
     * for buttons.
     * 
     * @param gameController the {@link GameController} to be associated with the
     *                       menu item actions
     * @return a {@link Function} that maps {@link MenuItem} to
     *         {@link FXButtonAdapter} instances
     */
    @Override
    public Function<MenuItem, GraphicAdapter<Node>> menuItemToView(final GameController<Node> gameController) {
        return m -> {
            var button = new FXButtonAdapter(DEFAULT_ID, m.name());
            button.onAction(m.action(), gameController);
            return button;
        };
    }
}
