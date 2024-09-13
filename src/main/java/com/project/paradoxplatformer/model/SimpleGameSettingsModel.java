package com.project.paradoxplatformer.model;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import com.project.paradoxplatformer.controller.gameloop.LoopManager;
import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.model.inputmodel.InputModel;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

public class SimpleGameSettingsModel implements GameSettingsModel {

    private final Map<String, MenuItem> settingMapping;

    public SimpleGameSettingsModel(final Map<String, MenuItem> settingsMapping) {
        this.settingMapping = settingsMapping;
    }

    @Override
    public InputModel<LoopManager> getSettingsInput() {
        return () -> Collections.unmodifiableMap(new EnumMap<InputType, Command<LoopManager>>(Map.of(
            InputType.ESCAPE, LoopManager::stop
        )));
    }

    @Override
    public Map<String, MenuItem> getSettingsItems() {
        return this.settingMapping;
    }

    public static final Map<String, MenuItem> basicSettings() {
        return new HashMap<>(Map.of(
            "MENUID1", new MenuItem("Menu", GameController::exitGame),
            "RETRYID1", new MenuItem("Retry", GameController::restartGame)
        ));
    }
    
}
