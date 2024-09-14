package com.project.paradoxplatformer.view.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

import java.util.Objects;
import java.util.Optional;

public final class GamePlatformView<C, K> implements GameView<C> {

    private final LevelDTO packedData;
    private final SecureWrapper<GraphicContainer<C, ?>> container;
    private List<GraphicAdapter<C>> setComponents;
    private final ViewMappingFactory<C> viewMappingFactory;
    private OffsetCorrector offsetCorrector;
    private boolean isFlipped;

    public GamePlatformView(
            final LevelDTO packedData,
            final GraphicContainer<C, ?> g,
            final ViewMappingFactory<C> factory) {
        this.packedData = packedData;
        this.viewMappingFactory = factory;
        this.container = SecureWrapper.of(g);// TO FIX
        this.offsetCorrector = null;
        this.setComponents = new ArrayList<>();
        this.isFlipped = false;
    }

    @Override
    public void init() {
        // NEED TO FIX, MAKE A BINDING OR SOME
        // CANT CHANGE CONTAINER DIMENSION HERE
        // CHECK::DONE
        final var gContainer = container.get();
        final Pair<DoubleProperty, DoubleProperty> dimScalingPropeties = this.initializePropreties(gContainer);
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
                .forEach(o -> o.bindPropreties(
                        dimScalingPropeties.getKey().divide(this.packedData.getWidth()),
                        dimScalingPropeties.getValue().divide(this.packedData.getHeight())));

        final OffsetFactory factory = new OffsetFactoryImpl(this.dimension());
        this.offsetCorrector = new GraphicOffsetCorrector(
                factory.bottomLeft(), // BETTER SEPARATE LAYOUT AND BOX IN FACTORY, MAKE A LIST
                factory.boxOffset(), // SO CAN USE REDUCE IN IMPLEMENTATIOn
                new Simple2DVector(1, -1));
    }

    @Override
    public List<GraphicAdapter<C>> getUnmodifiableControls() {
        return Optional.ofNullable(Collections.unmodifiableList(this.setComponents))
                .orElse(Collections.emptyList());
    }

    @Override
    public Dimension dimension() {
        return new Dimension(this.packedData.getWidth(), this.packedData.getHeight());
    }

    @Override
    public void updateControlState(ReadOnlyMutableObjectWrapper mutEntity, ReadOnlyGraphicDecorator<C> graphicCompo) {
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

    private Optional<GraphicAdapter<C>> retriveGraphic(final ReadOnlyGraphicDecorator<C> graphicCompo) {
        return this.setComponents.stream()
                .filter(g -> graphicCompo.getID() == g.getID())
                .findFirst();
    }

    @Override
    public void removeGraphic(final ReadOnlyGraphicDecorator<C> node) {
        retriveGraphic(node).ifPresent(this.setComponents::remove);
        System.out.println("DELETED? " + this.container.get().delete(node));
    }

    private Pair<DoubleProperty, DoubleProperty> initializePropreties(final GraphicContainer<C, ?> gContainer) {
        final DoubleProperty widthBinding = new SimpleDoubleProperty();
        final DoubleProperty heightBinding = new SimpleDoubleProperty();

        widthBinding.bind(gContainer.widthProperty());
        heightBinding.bind(gContainer.heightProperty());
        return Pair.of(widthBinding, heightBinding);
    }

}
