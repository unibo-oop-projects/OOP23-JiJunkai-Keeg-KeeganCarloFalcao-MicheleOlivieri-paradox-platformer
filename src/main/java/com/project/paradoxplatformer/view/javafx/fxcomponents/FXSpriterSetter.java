package com.project.paradoxplatformer.view.javafx.fxcomponents;

import java.util.List;
import java.util.Optional;

import com.project.paradoxplatformer.controller.deserialization.dtos.SpriteDTO;
import com.project.paradoxplatformer.utils.ImageLoader;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.sprites.Spriter;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class FXSpriterSetter implements Spriter<Image>{
    
    private String sheetPath;
    private Dimension bounds;
    private Image img;
    private Dimension tileSize;
    private SpriteDTO spriteFrames;
    

    public FXSpriterSetter(final String sheetPath, Dimension bounds, Dimension tileSize, final SpriteDTO spriteMeta) throws InvalidResourceException {
        this.sheetPath = sheetPath;
        this.bounds = bounds;
        this.tileSize = tileSize;
        this.loadSpriteSheet();
        this.spriteFrames = spriteMeta;
    }

    

    @Override
    public List<Image> getIdleImage() {
        return this.collection(0, tileSize.width() * spriteFrames.getIdleFrames());
    }

    @Override
    public List<Image> runningImages() {
        return this.collection(spriteFrames.getIdleFrames() * tileSize.width(), tileSize.width() * spriteFrames.getRunningFrames());
    }

    public List<Image> jumpingImages() {
        return List.of(this.img);
    }

    public List<Image> fallingImages() {
        return List.of(this.img);
    }

    private void loadSpriteSheet() throws InvalidResourceException {
        this.img = ImageLoader.FXImage(sheetPath);
    }

    private List<Image> collection(double init, double end) {
        return Optional.of(this.img)
            .filter(j -> tileSize.width() == bounds.width())
            .map(List::of)
            .orElse(Stream.iterate(init, x -> x < end, x -> x + tileSize.width())
                .map(this::createImage)
                .collect(Collectors.toList())
            );
    }

    private Image createImage(Double x) {
        return new WritableImage(
            this.img.getPixelReader(), 
            x.intValue(),
            0,
            (int)this.tileSize.width(), 
            (int)this.tileSize.height()
        );
    }
}
