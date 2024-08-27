package com.project.paradoxplatformer.view.javafx.fxcomponents;

import java.util.Optional;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;

import com.project.paradoxplatformer.utils.ImageLoader;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.javafx.fxcomponents.abstracts.AbstractFXGraphicAdapter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.image.ImageView;

public class FXImageAdapter extends AbstractFXGraphicAdapter {

    protected final ImageView imgComponent;
    
    private final DoubleProperty widthProperty;
    private final DoubleProperty heighProperty;

    protected FXImageAdapter(Dimension dimension, Coord2D position, String imageURL) throws InvalidResourceException {
        super(new ImageView(), dimension, position);

        if (this.uiComponent instanceof ImageView imgCopy) {
            this.imgComponent = imgCopy;
            this.imgComponent.setPreserveRatio(true);
            this.imgComponent.setSmooth(true);
            widthProperty = new SimpleDoubleProperty(dimension.width());
            heighProperty = new SimpleDoubleProperty(dimension.height());
            imgComponent.setImage(ImageLoader.FXImage(imageURL));
            this.setDimension(dimension.width(), dimension.height());
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
        this.widthProperty.set(width);
        this.heighProperty.set(height);
    }

    @Override
    public void bindPropreties(ObservableDoubleValue wratio, ObservableDoubleValue hratio) {
        super.bindPropreties(wratio, hratio);
        this.imgComponent.fitHeightProperty().bind(heighProperty.multiply(hratio));
        this.imgComponent.fitWidthProperty().bind(widthProperty.multiply(wratio));
    }
}
