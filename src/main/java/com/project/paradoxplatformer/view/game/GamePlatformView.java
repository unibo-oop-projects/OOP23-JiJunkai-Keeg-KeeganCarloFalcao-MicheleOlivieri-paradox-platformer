package com.project.paradoxplatformer.view.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.project.paradoxplatformer.Views;
import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.model.world.mappings.EntityDataMapper;
import com.project.paradoxplatformer.model.world.mappings.view.ViewMappingFactory;
import com.project.paradoxplatformer.model.world.mappings.view.ViewMappingFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.GraphicComponent;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;

import javafx.scene.Node;

import java.util.Objects;
import java.util.Optional;

import java.util.LinkedList;

public class GamePlatformView implements GameView {

    private final LevelDTO packedData;
    private final GraphicContainer<Node> container;
    private Set<GraphicComponent> listComponents;
    private final ViewMappingFactory viewMappingFactory;

    public GamePlatformView(final LevelDTO packedData, GraphicContainer<Node> g) {
        this.packedData = packedData;
        this.viewMappingFactory = new ViewMappingFactoryImpl();
        this.container = g;
    }

    @Override
    public void init() {
        //NEED TO FIX, MAKE A BINDING OR SOME
        //CANT CHANGE CONTAINER DIMENSION HERE
        this.container.setDimension(this.packedData.getWidth() * Views.SIZE_FACTOR,this.packedData.getHeight() * Views.SIZE_FACTOR);

        this.listComponents = new LinkedList<>(List.of(
                this.forkGraphic(g -> !g.getImage().isEmpty(), this.viewMappingFactory.imageToView()),
                this.forkGraphic(g -> Objects.nonNull(g.getColor()), this.viewMappingFactory.blockToView())
            ))
            .stream()
            .flatMap(Set::stream)
            .collect(Collectors.toSet());

        this.listComponents.forEach(this.container::render);
    }

    private Set<GraphicComponent> forkGraphic(final Predicate<GameDTO> objectsRendersPred, final EntityDataMapper<GraphicComponent> dtoToGraphic) {
        return Arrays.stream(this.packedData.getGameDTOs())
            .filter(objectsRendersPred)
            .map(dtoToGraphic::map)
            //FIX DUPLICATE KEYYS
            .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Set<GraphicComponent> getControls() {
        return Optional.of(this.listComponents)
            .filter(Objects::nonNull)
            .orElse(Collections.emptySet());
    }

    @Override
    public Dimension dimension() {
        return new Dimension(this.packedData.getWidth(), this.packedData.getHeight());
    }
    
}
