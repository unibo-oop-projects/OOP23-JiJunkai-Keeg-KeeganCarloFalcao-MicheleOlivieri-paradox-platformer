package com.project.paradoxplatformer.model.world;

import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.model.world.api.World;

public interface ModelData {

    void init();

    World getWorld();

    LevelDTO getPackedData();

    //shouldn' handle
    void stop();

    void resume();

}
