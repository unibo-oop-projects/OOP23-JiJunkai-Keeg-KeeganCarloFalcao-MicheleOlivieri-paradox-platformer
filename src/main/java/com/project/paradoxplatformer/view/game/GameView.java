package com.project.paradoxplatformer.view.game;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.fxcomponents.api.GraphicComponent;

import java.util.Set;

public interface GameView {
    
    void init();

    Set<GraphicComponent> getControls();

    Dimension dimension();
}
