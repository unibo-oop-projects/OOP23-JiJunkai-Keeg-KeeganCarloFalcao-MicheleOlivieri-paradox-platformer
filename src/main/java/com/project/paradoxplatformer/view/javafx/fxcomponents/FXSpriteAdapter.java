package com.project.paradoxplatformer.view.javafx.fxcomponents;

import com.project.paradoxplatformer.controller.deserialization.dtos.SpriteDTO;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.graphics.sprites.SpriteAnimator;
import com.project.paradoxplatformer.view.graphics.sprites.SpriteStatus;
import com.project.paradoxplatformer.view.graphics.sprites.Spriteable;

import javafx.scene.image.Image;

public final class FXSpriteAdapter extends FXImageAdapter implements Spriteable<SpriteStatus>{

    private SpriteAnimator<Image> spriteAnimator;
    private boolean isSpecial;

    protected FXSpriteAdapter(final int id, Dimension dimension, Coord2D position, String imageURL, final SpriteDTO spriteMeta) throws InvalidResourceException {
        super(id, dimension, position, imageURL);
        this.spriteAnimator = new SpriteAnimator<Image>(
                    new FXSpriterSetter(
                            imageURL,
                            new Dimension(
                                    imgComponent.getImage().getWidth(),
                                    imgComponent.getImage().getHeight()
                                ),
                            dimension,
                            spriteMeta
                        ),
                    spriteMeta.getMinFrames()
                );
        this.isSpecial = spriteMeta.isSpecial();
    }

    @Override
    public void animate(SpriteStatus status) {
        this.spriteAnimator.selectFrame(status, imgComponent::setImage);
    }

    @Override
    public boolean isSpecial() {
        return this.isSpecial;
    }
    
}
