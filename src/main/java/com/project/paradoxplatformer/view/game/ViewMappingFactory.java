package com.project.paradoxplatformer.view.game;


import java.util.function.Function;

import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.model.MenuItem;
import com.project.paradoxplatformer.model.mappings.EntityDataMapper;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

public interface ViewMappingFactory<C> {

    EntityDataMapper<GraphicAdapter<C>> imageToView();

    EntityDataMapper<GraphicAdapter<C>> blockToView();

    Function<MenuItem, GraphicAdapter<C>> menuItemToView(GameController<C> gameController);
}
