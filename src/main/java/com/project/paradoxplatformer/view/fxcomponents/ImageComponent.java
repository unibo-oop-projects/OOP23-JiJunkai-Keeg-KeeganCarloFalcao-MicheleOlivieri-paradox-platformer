package com.project.paradoxplatformer.view.fxcomponents;

import java.util.Optional;

import com.project.paradoxplatformer.MainApplication;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.abstracts.AbstractGraphicComponent;
import com.project.paradoxplatformer.view.fxcomponents.api.Spriteable;
import com.project.paradoxplatformer.view.fxcomponents.api.SpriteStatus;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageComponent extends AbstractGraphicComponent implements Spriteable{

    private final ImageView imgComponent;
    private SpriteAnimator spriteAnimator;

    //MUST ADD WETHER AN IMAGE IS SPRITEABLE
    public ImageComponent(Node component, Dimension dimension, Coord2D position, String imageURL)  {
        super(component, dimension, position); 
        if (component instanceof ImageView imgCopy) {
            this.imgComponent = imgCopy;
            //SHOULD DO IF SPRITE SO MAKE DISTINCT CLASSES
            imgComponent.setImage(new Image(MainApplication.class.getResource(imageURL).toExternalForm()));
            this.setDimension(dimension.width(), dimension.height());
            System.out.println(this.imgComponent.getImage().getHeight());
            System.out.println(this.imgComponent.getImage().getWidth());
            this.spriteAnimator = new SpriteAnimator(
                    new SpriterSetter(
                        imageURL, 
                        new Dimension(
                            imgComponent.getImage().getWidth(),
                            imgComponent.getImage().getHeight()),
                        dimension
                    )
                );
            
            
        } else {
            throw new IllegalArgumentException("Requires imageview");
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

    @Override
    public void animate(SpriteStatus status) {
        
        this.spriteAnimator.selectFrame(status, this.imgComponent::setImage);
    }



}
