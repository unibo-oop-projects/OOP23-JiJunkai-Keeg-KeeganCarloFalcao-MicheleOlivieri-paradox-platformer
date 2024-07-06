package com.project.paradoxplatformer.view.graphics;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.renders.Component;

import javafx.scene.Node;

public interface GraphicComponent extends Component<Node>{

    Dimension dimension();

    Coord2D absolutePosition();

    Coord2D relativePosition();

    void setDimension(final double width, final double height);

    void setPosition(final double x, final double y);

    void translate(final double x, final double y);

    void flip();
    
}
