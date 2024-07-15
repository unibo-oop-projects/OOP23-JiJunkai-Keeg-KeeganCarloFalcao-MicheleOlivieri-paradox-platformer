package com.project.paradoxplatformer.view.game;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.project.paradoxplatformer.Views;
import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.world.mappings.EntityDataMapper;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.SecureWrapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.orientations.GraphicOffsetCorrector;
import com.project.paradoxplatformer.utils.geometries.orientations.OffsetCorrector;
import com.project.paradoxplatformer.utils.geometries.orientations.factory.OffsetFactory;
import com.project.paradoxplatformer.utils.geometries.orientations.factory.OffsetFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.vector.Simple2DVector;
import com.project.paradoxplatformer.view.fxcomponents.FXImageAdapter;
import com.project.paradoxplatformer.view.fxcomponents.ViewMappingFactoryImpl;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;
import com.project.paradoxplatformer.view.graphics.sprites.SpriteStatus;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventDispatcher;
import javafx.scene.Node;

import java.util.Objects;
import java.util.Optional;

import java.util.LinkedList;

public class GamePlatformView implements GameView {

    private final LevelDTO packedData;
    private final SecureWrapper<GraphicContainer<Node>> container;
    private Set<GraphicAdapter> listComponents;
    private final ViewMappingFactory viewMappingFactory;
    private OffsetCorrector offsetCorrector;
    private boolean isFlipped;

    public GamePlatformView(final LevelDTO packedData, GraphicContainer<Node> g) {
        this.packedData = packedData;
        this.viewMappingFactory = new ViewMappingFactoryImpl();
        this.container = SecureWrapper.of(g);
        this.offsetCorrector = null;
    }

    @Override
    public void init() throws InvalidResourceException {
        
        
        //NEED TO FIX, MAKE A BINDING OR SOME
        //CANT CHANGE CONTAINER DIMENSION HERE
        var f = container.get();
        f.setDimension(this.packedData.getWidth(),this.packedData.getHeight());
        
        this.listComponents = new LinkedList<>(List.of(
                this.forkGraphic(g -> Objects.nonNull(g.getImage()), this.viewMappingFactory.imageToView()),
                this.forkGraphic(g -> Objects.nonNull(g.getColor()), this.viewMappingFactory.blockToView())
            ))
            .stream()
            .flatMap(Set::stream)
            .collect(Collectors.toSet());

        final DoubleProperty l = new SimpleDoubleProperty();
        l.bind(container.get().widthProperty());
        
        final DoubleProperty l1 = new SimpleDoubleProperty();
        l1.bind(container.get().heightProperty());
        

        this.listComponents.stream()
            .filter(this.container.get()::render)
            .forEach(o -> o.bindPropreties(l.divide(this.packedData.getWidth()), l1.divide(this.packedData.getHeight())));
        
        final OffsetFactory factory = new OffsetFactoryImpl(this.dimension());
        this.offsetCorrector = new GraphicOffsetCorrector(
            factory.bottomLeft(), //BETTER SEPARATE LAYOUT AND BOX IN FACTORY, MAKE A LIST
            factory.boxOffset(),// SO CAN USE REDUCE IN IMPLEMENTATIOn
            new Simple2DVector(1, -1)
        );
    }

    private Set<GraphicAdapter> forkGraphic(final Predicate<GameDTO> objectsRendersPred, final EntityDataMapper<GraphicAdapter> dtoToGraphic) {
        return Arrays.stream(this.packedData.getGameDTOs())
            .filter(objectsRendersPred)
            .map(dtoToGraphic::map)
            //FIX DUPLICATE KEYYS
            .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public Set<GraphicAdapter> getControls() {
        return Optional.of(this.listComponents)
            .filter(Objects::nonNull)
            .orElse(Collections.emptySet());
    }

    @Override
    public Dimension dimension() {
        return new Dimension(this.packedData.getWidth(), this.packedData.getHeight());
    }

    @Override
    public void updateEnitityState(MutableObject m, GraphicAdapter g) {

        final Coord2D c = offsetCorrector.correct(g.dimension(), m.getPosition());
                        
        
        g.setPosition(c.x(), c.y());
    
        g.setDimension(m.getDimension().width(), m.getDimension().height());
        
        if(m instanceof PlayerModel pl) {
            //JUST FOR TESTING, MUST DO BETTER
            if(pl.getSp().x() < 0 && !this.isFlipped) {
                g.flip();
                this.isFlipped = true;
            } else if(pl.getSp().x() > 0 && this.isFlipped) {
                g.flip();
                this.isFlipped = false;
            }
            if(g instanceof FXImageAdapter gr) { 
                gr.animate(pl.getSpeed().magnitude() > 0 ? SpriteStatus.RUNNING : SpriteStatus.IDLE);
                
            }
        }
    }
    
}
