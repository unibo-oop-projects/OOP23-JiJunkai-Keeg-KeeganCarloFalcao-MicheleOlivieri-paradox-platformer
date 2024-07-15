package com.project.paradoxplatformer.view.fxcomponents;

import java.util.List;
import java.util.Optional;

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
    

    public FXSpriterSetter(final String sheetPath, Dimension bounds, Dimension tileSize) throws InvalidResourceException {
        this.sheetPath = sheetPath;
        this.bounds = bounds;
        this.tileSize = tileSize;
        this.loadSpriteSheet();
    }

    private void loadSpriteSheet() throws InvalidResourceException {
        this.img = ImageLoader.FXImage(sheetPath);
    }

    @Override
    public List<Image> getIdleImage() {
        return this.collection(0, tileSize.width() * 5);
    }

    @Override
    public List<Image> runningImages() {
        return this.collection(5 * tileSize.width(), tileSize.width() * 8);
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

    public List<Image> jumpingImages() {
        return List.of(this.img);
    }

    public List<Image> fallingImages() {
        return List.of(this.img);
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
