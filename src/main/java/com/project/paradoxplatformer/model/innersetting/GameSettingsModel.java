package com.project.paradoxplatformer.model.innersetting;

import com.project.paradoxplatformer.controller.gameloop.LoopManager;
import com.project.paradoxplatformer.model.inputmodel.InputModel;

import java.util.Map;

/**
 * Represents a model for managing game settings.
 * <p>
 * This interface defines methods for retrieving input models and settings items
 * used in the game's settings menu.
 * </p>
 */
public interface GameSettingsModel {

    /**
     * Returns the input model associated with the game settings.
     * <p>
     * The input model provides access to the settings inputs required by the
     * {@link LoopManager} for managing game loops.
     * </p>
     *
     * @return an {@link InputModel} for {@link LoopManager}
     */
    InputModel<LoopManager> getSettingsInput();

    /**
     * Returns a map of settings items available in the game's settings menu.
     * <p>
     * The map keys are the names of the settings items, and the values are
     * {@link MenuItem} instances that define the available options and actions.
     * </p>
     *
     * @return a {@link Map} of setting item names to {@link MenuItem} instances
     */
    Map<String, MenuItem> getSettingsItems();
}
