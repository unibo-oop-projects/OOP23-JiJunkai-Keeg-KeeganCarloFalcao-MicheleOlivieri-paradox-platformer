package com.project.paradoxplatformer.view.fxcomponents;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.fxcomponents.containers.GraphicContainer;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageComponent extends AbstractGraphicComponent{

    private ImageView imgComponent;

    public ImageComponent(Node component, Dimension dimension, String imageURL)  {
        super(component, dimension); 
        if (component instanceof ImageView imgCopy) {
            this.imgComponent = imgCopy;
            imgComponent.setImage(new Image(ImageComponent.class.getResource(imageURL).toExternalForm()));
            this.setDimension(dimension.width(), dimension.height());
            
        } else {
            throw new IllegalArgumentException("Require imageview");
        }
    }

    @Override
    public ImageView unwrap() {
        return this.imgComponent;
    }

    @Override
    public Optional<Image> image() {
        return Optional.of(this.imgComponent.getImage());
    }

    @Override
    public Optional<Color> color() {
        return Optional.empty();
    }

    @Override
    public void setDimension(double width, double height) {
        this.imgComponent.setFitHeight(height);
        this.imgComponent.setFitWidth(width);
    }

}
