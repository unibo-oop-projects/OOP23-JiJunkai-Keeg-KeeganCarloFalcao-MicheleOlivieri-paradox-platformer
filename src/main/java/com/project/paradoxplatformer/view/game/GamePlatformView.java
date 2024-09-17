package com.project.paradoxplatformer.view.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.model.entity.ReadOnlyMutableObjectWrapper;
import com.project.paradoxplatformer.utils.SecureWrapper;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.orientations.GraphicOffsetCorrector;
import com.project.paradoxplatformer.utils.geometries.orientations.OffsetCorrector;
import com.project.paradoxplatformer.utils.geometries.orientations.factory.OffsetFactory;
import com.project.paradoxplatformer.utils.geometries.orientations.factory.OffsetFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.vector.api.Simple2DVector;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;
import com.project.paradoxplatformer.view.graphics.ReadOnlyGraphicDecorator;
import com.project.paradoxplatformer.view.graphics.sprites.SpriteStatus;
import com.project.paradoxplatformer.view.javafx.fxcomponents.FXSpriteAdapter;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Represents a view for the game platform, responsible for managing and
 * updating
 * graphical components based on the level data and game state.
 * 
 * @param <C> the type of the graphics context
 * @param <K> the type of key used in the view mapping
 */
public final class GamePlatformView<C, K> implements GameView<C> {

    private final LevelDTO packedData;
    private final SecureWrapper<GraphicContainer<C, ?>> container;
    private List<GraphicAdapter<C>> setComponents;
    private final ViewMappingFactory<C> viewMappingFactory;
    private OffsetCorrector offsetCorrector;
    private boolean isFlipped;

    /**
     * Constructs a {@link GamePlatformView} with the specified level data, graphic
     * container, and view mapping factory.
     * 
     * @param packedData the level data containing game information
     * @param g          the graphic container to hold and manage graphics
     * @param factory    the factory for mapping data to graphical components
     */
    public GamePlatformView(
            final LevelDTO packedData,
            final GraphicContainer<C, ?> g,
            final ViewMappingFactory<C> factory) {
        this.packedData = packedData;
        this.viewMappingFactory = factory;
        this.container = SecureWrapper.of(g); // TO FIX
        this.offsetCorrector = null;
        this.setComponents = new ArrayList<>();
        this.isFlipped = false;
    }

    /**
     * Initializes the view by setting up the graphic components and dimensions
     * based on the level data.
     * <p>
     * This method sets the dimensions of the graphic container, initializes graphic
     * components, and sets up
     * the offset corrector for positioning graphics correctly.
     * </p>
     */
    @Override
    public void init() {
        // NEED TO FIX, MAKE A BINDING OR SOME
        // CANT CHANGE CONTAINER DIMENSION HERE
        // CHECK::DONE
        final var gContainer = container.get();
        final Pair<DoubleProperty, DoubleProperty> dimScalingProperties = this.initializeProperties(gContainer);
        gContainer.setDimension(this.packedData.getWidth(), this.packedData.getHeight());

        this.setComponents = Arrays.stream(this.packedData.getGameDTOs())
                .collect(Collectors.teeing(
                        Collectors.filtering(g -> Objects.nonNull(g.getImage()),
                                Collectors.mapping(this.viewMappingFactory.imageToView()::map, Collectors.toList())),
                        Collectors.filtering(g -> Objects.nonNull(g.getColor()),
                                Collectors.mapping(this.viewMappingFactory.blockToView()::map, Collectors.toList())),
                        (l1, l2) -> Stream.of(l1, l2).flatMap(List::stream).collect(Collectors.toList())));

        this.setComponents.stream()
                .filter(this.container.get()::render)
                .forEach(o -> o.bindProperties(
                        dimScalingProperties.getKey().divide(this.packedData.getWidth()),
                        dimScalingProperties.getValue().divide(this.packedData.getHeight())));

        final OffsetFactory factory = new OffsetFactoryImpl(this.dimension());
        this.offsetCorrector = new GraphicOffsetCorrector(
                factory.bottomLeft(), // BETTER SEPARATE LAYOUT AND BOX IN FACTORY, MAKE A LIST
                factory.boxOffset(), // SO CAN USE REDUCE IN IMPLEMENTATION
                new Simple2DVector(1, -1));
    }

    /**
     * Returns an unmodifiable list of graphic components associated with the view.
     * 
     * @return an unmodifiable list of {@link GraphicAdapter} objects
     */
    @Override
    public List<GraphicAdapter<C>> getUnmodifiableControls() {
        return Optional.ofNullable(Collections.unmodifiableList(this.setComponents))
                .orElse(Collections.emptyList());
    }

    /**
     * Returns the dimension of the game platform as defined by the level data.
     * 
     * @return a {@link Dimension} object representing the width and height of the
     *         game platform
     */
    @Override
    public Dimension dimension() {
        return new Dimension(this.packedData.getWidth(), this.packedData.getHeight());
    }

    /**
     * Updates the state of the graphical component based on the given mutable
     * entity and graphic component.
     * <p>
     * This method adjusts the position and dimension of the graphic component,
     * animates sprites, and handles
     * flipping the sprite based on the player's speed.
     * </p>
     * 
     * @param mutEntity    the mutable entity containing updated game state
     *                     information
     * @param graphicCompo the read-only graphic component to be updated
     */
    @Override
    public void updateControlState(final ReadOnlyMutableObjectWrapper mutEntity,
            final ReadOnlyGraphicDecorator<C> graphicCompo) {
        retriveGraphic(graphicCompo).ifPresent(graph -> {

            final var c = offsetCorrector.correct(graphicCompo.dimension(), mutEntity.getPosition());
            graph.setPosition(c.x(), c.y());
            graph.setDimension(mutEntity.getDimension().width(), mutEntity.getDimension().height());

            if (graph instanceof FXSpriteAdapter spriAdapter && !spriAdapter.isSpecial()) {
                spriAdapter.animate(SpriteStatus.IDLE);
            }

            if (mutEntity.getCollisionType().equals(CollisionType.PLAYER)) {
                // JUST FOR TESTING, MUST DO BETTER
                if (mutEntity.getSpeed().xComponent() < 0 && !this.isFlipped) {
                    graph.flip();
                    this.isFlipped = true;
                } else if (mutEntity.getSpeed().xComponent() > 0 && this.isFlipped) {
                    graph.flip();
                    this.isFlipped = false;
                }

                // System.out.println("[Player Position]: " + mutEntity.getSpeed());

                if (graph instanceof FXSpriteAdapter spriAdapter) {
                    spriAdapter
                            .animate(mutEntity.getSpeed().magnitude() > mutEntity.getBaseDelta() ? SpriteStatus.RUNNING
                                    : SpriteStatus.IDLE);
                }
            }
        });
    }

    /**
     * Retrieves the graphic adapter corresponding to the given graphic component.
     * 
     * @param graphicCompo the read-only graphic component
     * @return an {@link Optional} containing the {@link GraphicAdapter} if found,
     *         otherwise empty
     */
    private Optional<GraphicAdapter<C>> retriveGraphic(final ReadOnlyGraphicDecorator<C> graphicCompo) {
        return this.setComponents.stream()
                .filter(g -> graphicCompo.getID() == g.getID())
                .findFirst();
    }

    /**
     * Removes the specified graphic component from the view.
     * 
     * @param node the read-only graphic component to be removed
     */
    @Override
    public void removeGraphic(final ReadOnlyGraphicDecorator<C> node) {
        retriveGraphic(node).ifPresent(this.setComponents::remove);
        System.out.println("DELETED? " + this.container.get().delete(node));
    }

    /**
     * Initializes properties for binding width and height dimensions to the graphic
     * container.
     * 
     * @param gContainer the graphic container to bind properties to
     * @return a {@link Pair} of {@link DoubleProperty} for width and height
     */
    private Pair<DoubleProperty, DoubleProperty> initializeProperties(final GraphicContainer<C, ?> gContainer) {
        final DoubleProperty widthBinding = new SimpleDoubleProperty();
        final DoubleProperty heightBinding = new SimpleDoubleProperty();

        widthBinding.bind(gContainer.widthProperty());
        heightBinding.bind(gContainer.heightProperty());
        return Pair.of(widthBinding, heightBinding);
    }

}
