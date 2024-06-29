package com.project.paradoxplatformer.view.fxcomponents.containers.api;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.fxcomponents.api.GraphicComponent;
import com.project.paradoxplatformer.view.fxcomponents.keyinputs.api.KeyInputer;

public interface GraphicContainer extends KeyInputer{

    Dimension dimension();

    void setDimension(final double width, final double height);
    
    boolean render(GraphicComponent component);



}
