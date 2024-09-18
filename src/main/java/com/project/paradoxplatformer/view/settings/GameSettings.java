package com.project.paradoxplatformer.view.settings;

import java.util.List;

import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

/**
 * Represents the settings for a game.
 * <p>
 * This interface defines methods for initializing settings and retrieving
 * a list of graphic adapters that represent controls in the game settings.
 * </p>
 *
 * @param <C> the type of the context associated with the graphic adapters
 */
public interface GameSettings<C> {

    /**
     * Initializes the game settings.
     * <p>
     * This method should set up any necessary state or configurations
     * required for the game settings to be properly loaded and used.
     * </p>
     */
    void init();

    /**
     * Retrieves an unmodifiable list of graphic adapters representing game
     * controls.
     * <p>
     * The list provides the graphical representations of the controls that
     * are used in the game settings interface. The list is unmodifiable
     * to prevent changes to the controls after initialization.
     * </p>
     *
     * @return an unmodifiable {@link List} of {@link GraphicAdapter} instances
     */
    List<GraphicAdapter<C>> getUnmodifiableControls();
}
