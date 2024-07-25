package com.project.paradoxplatformer.view.game;


import com.project.paradoxplatformer.model.mappings.EntityDataMapper;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

public interface ViewMappingFactory<C> {

    EntityDataMapper<GraphicAdapter<C>> imageToView() throws InvalidResourceException;

    EntityDataMapper<GraphicAdapter<C>> blockToView();
}
