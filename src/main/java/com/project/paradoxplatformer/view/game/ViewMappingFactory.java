package com.project.paradoxplatformer.view.game;


import com.project.paradoxplatformer.model.mappings.EntityDataMapper;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

public interface ViewMappingFactory<C> {

    EntityDataMapper<GraphicAdapter<C>> imageToView();

    EntityDataMapper<GraphicAdapter<C>> blockToView();
}
