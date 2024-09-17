package com.project.paradoxplatformer.view.game;

import java.util.function.Function;

import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.model.MenuItem;
import com.project.paradoxplatformer.model.mappings.EntityDataMapper;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

/**
 * Factory interface for mapping different types of game elements to their
 * corresponding views.
 * <p>
 * This interface provides methods for converting game-related data into view
 * representations, including images, blocks, and menu items.
 * </p>
 *
 * @param <C> The type of the context used by the {@link GraphicAdapter}
 */
public interface ViewMappingFactory<C> {

    /**
     * Provides a mapping from images to their corresponding {@link GraphicAdapter}.
     *
     * @return an {@link EntityDataMapper} that maps image data to
     *         {@link GraphicAdapter} instances
     */
    EntityDataMapper<GraphicAdapter<C>> imageToView();

    /**
     * Provides a mapping from blocks to their corresponding {@link GraphicAdapter}.
     *
     * @return an {@link EntityDataMapper} that maps block data to
     *         {@link GraphicAdapter} instances
     */
    EntityDataMapper<GraphicAdapter<C>> blockToView();

    /**
     * Provides a function that maps {@link MenuItem} instances to their
     * corresponding {@link GraphicAdapter}, using the provided
     * {@link GameController}.
     * <p>
     * This method allows for the dynamic creation of views based on menu items
     * and the specific game controller context.
     * </p>
     *
     * @param gameController the {@link GameController} used to provide context for
     *                       the view mapping
     * @return a {@link Function} that maps {@link MenuItem} to
     *         {@link GraphicAdapter}
     */
    Function<MenuItem, GraphicAdapter<C>> menuItemToView(GameController<C> gameController);
}
