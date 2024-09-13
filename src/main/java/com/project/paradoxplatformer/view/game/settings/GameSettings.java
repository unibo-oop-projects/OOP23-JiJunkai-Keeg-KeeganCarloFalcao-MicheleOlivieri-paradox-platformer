package com.project.paradoxplatformer.view.game.settings;

import java.util.List;

import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

public interface GameSettings<C> {
    
    void init();

    List<GraphicAdapter<C>> getUnmodifiableControls();
}
