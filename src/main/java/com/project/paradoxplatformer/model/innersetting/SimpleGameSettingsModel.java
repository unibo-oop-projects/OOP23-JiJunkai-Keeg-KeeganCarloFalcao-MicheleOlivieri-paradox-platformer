package com.project.paradoxplatformer.model.innersetting;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;
import com.project.paradoxplatformer.controller.gameloop.LoopManager;
import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.model.inputmodel.InputModel;

/**
 * A simple implementation of {@link GameSettingsModel} that provides basic game
 * settings.
 * <p>
 * This class maintains a mapping of setting items and provides an input model
 * for managing game settings. It also includes a static method for creating a
 * basic set of settings.
 * </p>
 */
public class SimpleGameSettingsModel implements GameSettingsModel {

    private final Map<String, MenuItem> settingMapping;

    /**
     * Constructs a {@link SimpleGameSettingsModel} with the specified settings
     * mapping.
     * <p>
     * This constructor initializes the model with a map of setting items.
     * </p>
     *
     * @param settingsMapping a map of setting item names to {@link MenuItem}
     *                        instances
     */
    public SimpleGameSettingsModel(final Map<String, MenuItem> settingsMapping) {
        this.settingMapping = Optional.of(settingsMapping).get();
    }

    /**
     * Returns the input model associated with the game settings.
     * <p>
     * The input model provides a map of input types to commands that handle
     * settings-related actions, such as stopping the game loop when the escape
     * key is pressed.
     * </p>
     *
     * @return an {@link InputModel} for {@link LoopManager}
     */
    @Override
    public InputModel<LoopManager> getSettingsInput() {
        return () -> Collections.unmodifiableMap(new EnumMap<>(Map.of(
                InputType.ESCAPE, LoopManager::stop)));
    }

    /**
     * Returns a map of settings items available in the game's settings menu.
     * <p>
     * The map keys are the names of the settings items, and the values are
     * {@link MenuItem} instances that define the available options and actions.
     * </p>
     *
     * @return a {@link Map} of setting item names to {@link MenuItem} instances
     */
    @Override
    public Map<String, MenuItem> getSettingsItems() {
        return Optional.of(this.settingMapping).get();
    }

    /**
     * Creates a basic set of settings with predefined menu items.
     * <p>
     * This static method returns a {@link Map} containing default settings items
     * for menu and retry actions.
     * </p>
     *
     * @return a {@link Map} with basic settings items
     */
    public static Map<String, MenuItem> basicSettings() {
        return new HashMap<>(Map.of(
                "MENUID1", new MenuItem("Menu", GameController::exitGame),
                "RETRYID1", new MenuItem("Retry", GameController::restartGame)));
    }

}
