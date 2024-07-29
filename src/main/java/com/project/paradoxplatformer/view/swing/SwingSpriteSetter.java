package com.project.paradoxplatformer.view.swing;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.project.paradoxplatformer.utils.ImageLoader;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.sprites.Spriter;

public class SwingSpriteSetter implements Spriter<BufferedImage>{
   
    private String sheetPath;
    private Dimension bounds;
    private BufferedImage img;
    private Dimension tileSize;
    

    public SwingSpriteSetter(final String sheetPath, Dimension bounds, Dimension tileSize) throws InvalidResourceException, IOException {
        this.sheetPath = sheetPath;
        this.bounds = bounds;
        this.tileSize = tileSize;
        this.loadSpriteSheet();
    }

    private void loadSpriteSheet() throws InvalidResourceException, IOException {
        this.img = ImageLoader.AWTImage(sheetPath);
    }

    @Override
    public List<BufferedImage> getIdleImage() {
        return this.collection(0, tileSize.width() * 5);
    }

    @Override
    public List<BufferedImage> runningImages() {
        return this.collection(5 * tileSize.width(), tileSize.width() * 8);
    }

    private List<BufferedImage> collection(double init, double end) {
        return Optional.of(this.img)
            .filter(j -> tileSize.width() == bounds.width())
            .map(List::of)
            .orElse(Stream.iterate(init, x -> x < end, x -> x + tileSize.width())
                .map(Double::intValue)
                .map(x -> this.getSubImage(this.img, x, 0, (int) this.tileSize.width(), (int) this.tileSize.height()))
                .collect(Collectors.toList())
            );
    }

    public List<BufferedImage> jumpingImages() {
        return List.of(this.img);
    }

    public List<BufferedImage> fallingImages() {
        return List.of(this.img);
    }

    private BufferedImage getSubImage(BufferedImage original, int x, int y, int width, int height) {
        return original.getSubimage(x, y, width, height);
    }
    
}
