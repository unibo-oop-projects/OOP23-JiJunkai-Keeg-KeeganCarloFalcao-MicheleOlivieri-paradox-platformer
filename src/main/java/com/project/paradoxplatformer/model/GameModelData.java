package com.project.paradoxplatformer.model;

import java.util.function.Consumer;

import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.world.api.World;

public interface GameModelData {

    void init();

    World getWorld();

    void rebuild();

    void actionOnWorld(Consumer<World> action);
}
