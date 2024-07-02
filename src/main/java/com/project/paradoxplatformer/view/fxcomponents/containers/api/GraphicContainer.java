package com.project.paradoxplatformer.view.fxcomponents.containers.api;

import com.project.paradoxplatformer.controller.input.api.KeyInputer;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.renders.Component;

public interface GraphicContainer extends KeyInputer{

    Dimension dimension();

    void setDimension(final double width, final double height);
    
    boolean render(final Component component);



}
