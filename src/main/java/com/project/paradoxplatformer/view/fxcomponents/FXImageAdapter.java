package com.project.paradoxplatformer.view.fxcomponents;

import java.util.Optional;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.project.paradoxplatformer.utils.ImageLoader;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.abstracts.AbstractFXGraphicAdapter;
import com.project.paradoxplatformer.view.graphics.sprites.SpriteAnimator;
import com.project.paradoxplatformer.view.graphics.sprites.SpriteStatus;
import com.project.paradoxplatformer.view.graphics.sprites.Spriteable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FXImageAdapter extends AbstractFXGraphicAdapter implements Spriteable<SpriteStatus>{

    private final ImageView imgComponent;
    private SpriteAnimator<Image> spriteAnimator;

    //MUST ADD WETHER AN IMAGE IS SPRITEABLE
    protected FXImageAdapter(Dimension dimension, Coord2D position, String imageURL)  {
        super(new ImageView(), dimension, position); 
        if (this.uiComponent instanceof ImageView imgCopy) {
            this.imgComponent = imgCopy;
            //SHOULD DO IF SPRITE SO MAKE DISTINCT CLASSES
            imgComponent.setImage(ImageLoader.FXImage(imageURL));
            this.setDimension(dimension.width(), dimension.height());
            this.spriteAnimator = new SpriteAnimator<Image>(
                    new FXSpriterSetter(
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

    protected Optional<String> image() {
        return Optional.of(this.imgComponent.getImage())
            .filter(Objects::nonNull)
            .map(javafx.scene.image.Image::getUrl)
            .map(i -> i.split(File.pathSeparator))
            .map(Arrays::stream)
            .flatMap(s -> s.reduce((a, b) -> b));
            
    }

    @Override
    public void setDimension(double width, double height) {
        this.imgComponent.setFitHeight(height);
        this.imgComponent.setFitWidth(width);
    }

    @Override
    public void animate(SpriteStatus status) {
        this.spriteAnimator.selectFrame(status, imgComponent::setImage);
    }
}
