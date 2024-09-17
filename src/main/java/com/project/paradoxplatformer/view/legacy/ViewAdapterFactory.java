package com.project.paradoxplatformer.view.legacy;

import java.util.function.Function;
import java.util.function.Supplier;

import com.project.paradoxplatformer.view.game.ViewMappingFactory;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;
import com.project.paradoxplatformer.view.manager.ViewManager;

public interface ViewAdapterFactory<V, P, K> {

    Supplier<ViewManager> mainAppManager();

    Supplier<ViewMappingFactory<V>> getComponentsFactory();

    Function<P, GraphicContainer<V, K>> containerMapper();

    P blankPage();

    P loadingPage();
}
