package com.project.paradoxplatformer.view.game;

import com.project.paradoxplatformer.model.entity.ReadOnlyMutableObjectWrapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;
import com.project.paradoxplatformer.view.graphics.ReadOnlyGraphicDecorator;

import java.util.Set;

public interface GameView<C> {

    void init();

    Set<GraphicAdapter<C>> getUnmodifiableControls();

    Dimension dimension();

    void updateControlState(ReadOnlyMutableObjectWrapper readOnlyMutable, ReadOnlyGraphicDecorator<C> readOnlyGraphic);
}
