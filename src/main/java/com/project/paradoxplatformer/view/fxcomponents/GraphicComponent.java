package com.project.paradoxplatformer.view.fxcomponents;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.renders.Component;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Optional;

public interface GraphicComponent extends Component{

    Dimension dimension();

    Coord2D position();

    void setDimension(final double width, final double height);

    void setPosition(final double x, final double y);

    Optional<Image> image();

    Optional<Color> color();
    
}
