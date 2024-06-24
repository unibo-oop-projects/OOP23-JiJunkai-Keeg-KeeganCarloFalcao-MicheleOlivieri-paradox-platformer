package com.project.paradoxplatformer.view.fxcomponents.containers.api;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.fxcomponents.api.GraphicComponent;

public interface GraphicContainer{

    Dimension dimension();

    void setDimension(final double width, final double height);
    
    boolean render(GraphicComponent component);

}
