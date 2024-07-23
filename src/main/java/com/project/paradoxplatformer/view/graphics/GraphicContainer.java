package com.project.paradoxplatformer.view.graphics;

import com.project.paradoxplatformer.controller.input.api.KeyInputer;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.renders.ViewComponent;

import javafx.beans.value.ObservableDoubleValue;

public interface GraphicContainer<T> extends KeyInputer{

    Dimension dimension();

    void setDimension(final double width, final double height);
    
    boolean render(final ViewComponent<T> component);

    boolean delete(final ViewComponent<T> component);

    ObservableDoubleValue widthProperty();

    ObservableDoubleValue heightProperty();



}
