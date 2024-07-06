package com.project.paradoxplatformer.model.world.mappings.view;

import com.project.paradoxplatformer.model.world.mappings.EntityDataMapper;
import com.project.paradoxplatformer.view.graphics.GraphicComponent;

public interface ViewMappingFactory {

    EntityDataMapper<GraphicComponent> imageToView();

    EntityDataMapper<GraphicComponent> blockToView();
}
