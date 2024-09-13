package com.project.paradoxplatformer.view.game.settings;

import java.util.ArrayList;
import java.util.List;

import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.model.GameSettingsModel;
import com.project.paradoxplatformer.view.game.ViewMappingFactory;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;

public class SimpleGameSettings<C> implements GameSettings<C>{


        private List<GraphicAdapter<C>> listComponents;
        private final ViewMappingFactory<C> viewMappingFactory;
        private final GameSettingsModel model;
        private final GraphicContainer<C, ?> graphiContainer;
        private final GameController<C> gameController;

        public SimpleGameSettings(GameSettingsModel model, GameController<C> gameController, final GraphicContainer<C, ?> g,final ViewMappingFactory<C> factory) {
        this.viewMappingFactory = factory;
        this.listComponents = new ArrayList<>();
        this.model = model;
        this.graphiContainer = g;
        this.gameController = gameController;
    }


    @Override
    public void init() {
        this.listComponents = this.model.getSettingsItems().values().stream()
            .map(viewMappingFactory.menuItemToView(this.gameController))
            .peek(this.graphiContainer::render)
            .toList();
    }

    @Override
    public List<GraphicAdapter<C>> getUnmodifiableControls() {
        return this.listComponents;
    }
    
}
