package com.project.paradoxplatformer.view.fxcomponents;

import java.util.Optional;

import com.project.paradoxplatformer.utils.geometries.Dimension;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageComponent extends AbstractGraphicComponent{

    private ImageView imgComponent;

    public ImageComponent(Node component, String imageURL) {
        super(component); 
        if (component instanceof ImageView imgComponent) {
            imgComponent.setImage(new Image(imageURL));
        }
    }

    @Override
    public ImageView unwrap() {
        return this.imgComponent;
    }

    @Override
    public Dimension dimension() {
        return new Dimension(this.imgComponent.getFitWidth(), this.imgComponent.getFitHeight());
    }

    @Override
    public Optional<Image> image() {
        return Optional.of(this.imgComponent.getImage());
    }

    @Override
    public Optional<Color> color() {
        return Optional.empty();
    }
    
}
