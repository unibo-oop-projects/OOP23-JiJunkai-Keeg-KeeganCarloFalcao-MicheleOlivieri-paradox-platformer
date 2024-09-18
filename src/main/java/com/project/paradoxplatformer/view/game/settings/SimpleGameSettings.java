package com.project.paradoxplatformer.view.game.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.model.GameSettingsModel;
import com.project.paradoxplatformer.view.game.ViewMappingFactory;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;

/**
 * Implementation of {@link GameSettings} that manages game settings
 * and provides graphical representations of controls.
 * <p>
 * This class initializes settings based on the provided model and
 * updates the view with graphical components that represent those settings.
 * </p>
 *
 * @param <C> the type of the context associated with the graphic adapters
 */
public class SimpleGameSettings<C> implements GameSettings<C> {

    private List<GraphicAdapter<C>> listComponents;
    private final ViewMappingFactory<C> viewMappingFactory;
    private final GameSettingsModel model;
    private final GraphicContainer<C, ?> graphiContainer;
    private final GameController<C> gameController;

    /**
     * Constructs a {@link SimpleGameSettings} instance with the specified
     * parameters.
     * <p>
     * Initializes the settings with the provided model, game controller,
     * graphic container, and view mapping factory.
     * </p>
     *
     * @param model          the {@link GameSettingsModel} providing settings data
     * @param gameController the {@link GameController} for controlling game actions
     * @param g              the {@link GraphicContainer} for rendering graphical
     *                       components
     * @param factory        the {@link ViewMappingFactory} for mapping menu items
     *                       to views
     */
    public SimpleGameSettings(final GameSettingsModel model, final GameController<C> gameController,
            final GraphicContainer<C, ?> g,
            final ViewMappingFactory<C> factory) {
        this.viewMappingFactory = factory;
        this.listComponents = new ArrayList<>();
        this.model = model;
        this.graphiContainer = Optional.of(g).get();
        this.gameController = gameController;
    }

    /**
     * Initializes the game settings by creating and rendering graphic components
     * based on the settings model.
     * <p>
     * This method maps menu items from the settings model to graphical
     * representations,
     * and renders these components using the provided graphic container.
     * </p>
     */
    @Override
    public void init() {
        this.listComponents = this.model.getSettingsItems().values().stream()
                .map(viewMappingFactory.menuItemToView(this.gameController))
                .peek(this.graphiContainer::render)
                .toList();
    }

    /**
     * Retrieves an unmodifiable list of graphical components representing game
     * controls.
     * <p>
     * The list is a snapshot of the components created during initialization and
     * cannot be modified. This ensures that the controls remain consistent.
     * </p>
     *
     * @return an unmodifiable {@link List} of {@link GraphicAdapter} instances
     */
    @Override
    public List<GraphicAdapter<C>> getUnmodifiableControls() {
        return List.copyOf(this.listComponents);
    }
}
