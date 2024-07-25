package com.project.paradoxplatformer.view.game;

import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;

import java.util.Set;

public interface GameView<C> {

    void init() throws InvalidResourceException;

    Set<GraphicAdapter<C>> getUnmodifiableControls();

    Dimension dimension();

    void updateEnitityState(MutableObject m, GraphicAdapter<C> g);
}
