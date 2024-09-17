package com.project.paradoxplatformer.view.javafx.fxcomponents;

import java.util.Optional;
import java.io.File;
import java.util.Arrays;

import com.project.paradoxplatformer.utils.ImageLoader;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.javafx.fxcomponents.abstracts.AbstractFXGraphicAdapter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.image.ImageView;

/**
 * Adapter for handling image-based graphical components in JavaFX.
 * <p>
 * This class extends {@link AbstractFXGraphicAdapter} and is responsible for
 * adapting an {@link ImageView} to the abstract graphic adapter, allowing
 * the use of image-based components with specific dimensions and positions.
 * </p>
 */
public class FXImageAdapter extends AbstractFXGraphicAdapter {

    private final ImageView imgComponent;
    private final DoubleProperty widthProperty;
    private final DoubleProperty heightProperty;

    
    /**
     * Constructs an {@code FXImageAdapter} with specified dimensions, position, and
     * image URL.
     * 
     * @param dimension the dimension of the image
     * @param position  the position of the image
     * @param imageURL  the URL of the image to be loaded
     * @throws InvalidResourceException if the image resource is invalid
     */
    protected FXImageAdapter(final int id, Dimension dimension, Coord2D position, String imageURL) throws InvalidResourceException {
        super(id, new ImageView(), dimension, position);

        if (this.getUiComponent() instanceof ImageView imgCopy) {
            this.imgComponent = imgCopy;
            // this.imgComponent.setPreserveRatio(true);
            this.imgComponent.setSmooth(true);
            widthProperty = new SimpleDoubleProperty(dimension.width());
            heightProperty = new SimpleDoubleProperty(dimension.height());
            imgComponent.setImage(ImageLoader.createFXImage(imageURL));
            this.setDimension(dimension.width(), dimension.height());
        } else {
            throw new IllegalArgumentException("Requires ImageView");
        }
    }

    /**
     * Returns the URL of the image if present.
     * 
     * @return an {@link Optional} containing the image URL, or an empty
     *         {@link Optional} if the image is not loaded
     */
    protected Optional<String> image() {
        return Optional.ofNullable(this.imgComponent.getImage())
                .map(javafx.scene.image.Image::getUrl)
                .map(url -> url.split(File.pathSeparator))
                .map(Arrays::stream)
                .flatMap(s -> s.reduce((a, b) -> b));
    }

    /**
     * Sets the dimensions of the image component.
     * 
     * @param width  the new width of the image
     * @param height the new height of the image
     */
    @Override
    public void setDimension(final double width, final double height) {
        this.widthProperty.set(width);
        this.heightProperty.set(height);
    }

    /**
     * Binds the properties of the image component to the specified ratios.
     * 
     * @param wratio the width ratio to bind
     * @param hratio the height ratio to bind
     */
    @Override
    public void bindProperties(final ObservableDoubleValue wratio, final ObservableDoubleValue hratio) {
        super.bindProperties(wratio, hratio);
        this.imgComponent.fitHeightProperty().bind(heightProperty.multiply(hratio));
        this.imgComponent.fitWidthProperty().bind(widthProperty.multiply(wratio));
    }

    /**
     * Returns the {@link ImageView} component of this adapter.
     * 
     * @return the {@link ImageView} component
     */
    public ImageView getImageView() {
        return imgComponent;
    }
}
