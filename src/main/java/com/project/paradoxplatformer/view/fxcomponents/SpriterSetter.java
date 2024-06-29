package com.project.paradoxplatformer.view.fxcomponents;

import java.util.List;

import com.project.paradoxplatformer.MainApplication;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.fxcomponents.api.SpriteStatus;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.EnumMap;
import java.util.function.Consumer;
import java.util.Objects;

public class SpriterSetter implements Spriter{
    
    private String sheetPath;
    private Dimension bounds;
    private Image img;
    private Dimension tileSize;
    

    public SpriterSetter(final String sheetPath, Dimension bounds, Dimension tileSize) {
        this.sheetPath = sheetPath;
        this.bounds = bounds;
        this.tileSize = tileSize;
        this.loadSpriteSheet();
    }

    private void loadSpriteSheet() {
        this.img = new Image(MainApplication.class.getResource(this.sheetPath).toExternalForm());
        Objects.requireNonNull(this.img);
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
        if(tileSize.width() == bounds.width()) {
            return List.of(this.img);
        }
        return Stream.iterate(init, x -> x < end, x -> x + tileSize.width())
            .map(x -> new WritableImage(
                    this.img.getPixelReader(), 
                    x.intValue(),
                    0,
                    (int)this.tileSize.width(), 
                    (int)this.tileSize.height()
                )
            )
            .collect(Collectors.toList());
    }

    public List<Image> jumpingImages() {
        return List.of(this.img);
    }

    public List<Image> fallingImages() {
        return List.of(this.img);
    }
}
