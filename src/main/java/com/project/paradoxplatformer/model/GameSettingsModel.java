package com.project.paradoxplatformer.model;

import com.project.paradoxplatformer.controller.gameloop.LoopManager;
import com.project.paradoxplatformer.model.inputmodel.InputModel;

import java.util.Map;

public interface GameSettingsModel {
    
    InputModel<LoopManager> getSettingsInput();

    Map<String, MenuItem> getSettingsItems();
}
