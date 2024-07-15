package com.project.paradoxplatformer.view.game;

import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

import java.io.IOException;
import java.util.Set;

public interface GameView {
    
    void init() throws InvalidResourceException;

    Set<GraphicAdapter> getControls();

    Dimension dimension();

    void updateEnitityState(MutableObject m, GraphicAdapter g);
}
