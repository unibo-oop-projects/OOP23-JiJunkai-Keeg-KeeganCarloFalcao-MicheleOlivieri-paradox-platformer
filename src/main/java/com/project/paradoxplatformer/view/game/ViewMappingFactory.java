package com.project.paradoxplatformer.view.game;

import com.project.paradoxplatformer.model.world.mappings.EntityDataMapper;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

public interface ViewMappingFactory {

    EntityDataMapper<GraphicAdapter> imageToView();

    EntityDataMapper<GraphicAdapter> blockToView();
}
