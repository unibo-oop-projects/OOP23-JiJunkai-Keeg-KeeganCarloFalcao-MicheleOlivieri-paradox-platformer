package com.project.paradoxplatformer.controller.games;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.world.ModelData;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.model.world.mappings.EntityDataMapper;
import com.project.paradoxplatformer.model.world.mappings.view.ViewMappingFactory;
import com.project.paradoxplatformer.model.world.mappings.view.ViewMappingFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.orientations.GraphicOffsetCorrector;
import com.project.paradoxplatformer.utils.geometries.orientations.OffsetCorrector;
import com.project.paradoxplatformer.utils.geometries.orientations.factory.OffsetFactory;
import com.project.paradoxplatformer.utils.geometries.orientations.factory.OffsetFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.vector.Simple2DVector;
import com.project.paradoxplatformer.view.fxcomponents.ImageComponent;
import com.project.paradoxplatformer.view.fxcomponents.api.GraphicComponent;
import com.project.paradoxplatformer.view.fxcomponents.api.SpriteStatus;
import com.project.paradoxplatformer.view.fxcomponents.containers.api.GraphicContainer;

import java.util.function.Predicate;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

public class GameControllerImpl implements GameController{

    private final GraphicContainer gameView;
    private final ModelData gameModel;
    private Map<MutableObject, GraphicComponent> gamePair;
    private boolean isFlipped;
    private final ViewMappingFactory mFactory; //SHOULD DO IN CLASS
    private OffsetCorrector offsetCorrector;

    public GameControllerImpl(final ModelData gamemodel, final GraphicContainer gContainer) {
        this.gameModel = gamemodel;
        this.gameView = gContainer;
        this.mFactory = new ViewMappingFactoryImpl();
        //SHOULD GET FROM VIEW ACTUALLY
        
    }

    @Override
    public void loadModel() {
        this.gameModel.init();
    }

    //Need abstraction for view creation
    @Override
    public void syncView() {
        //MUST DO CREATE VIEW FOR VIEW CLASS
        this.gameView.setDimension(
            this.gameModel.getPackedData().getWidth(),
            this.gameModel.getPackedData().getHeight()
        );

        gamePair = this.flattenComponents(Set.of(
                this.syncGameObjects(g -> !g.getImage().isEmpty(), this.mFactory.imageToView()),
                this.syncGameObjects(g -> Objects.nonNull(g.getColor()), this.mFactory.blockToView())
            )
        );

        final OffsetFactory factory = new OffsetFactoryImpl(this.gameView.dimension());
        this.offsetCorrector = new GraphicOffsetCorrector(
            factory.bottomLeft(), //BETTER SEPARATE LAYOUT AND BOX IN FACTORY, MAKE A LIST
            factory.boxOffset(),// SO CAN USE REDUCE IN IMPLEMENTATIOn
            new Simple2DVector(1, -1)
        );
        
        
        //renders
        gamePair.entrySet().stream()
            .map(Map.Entry::getValue)//create a factory
            .forEach(this.gameView::render);

            
    }

    private Map<MutableObject, GraphicComponent> syncGameObjects(final Predicate<GameDTO> objectsRendersPred, final EntityDataMapper<GraphicComponent> dtoToGraphic) {
        return Arrays.stream(this.gameModel.getPackedData().getGameDTOs())
            .filter(objectsRendersPred)
            .map(dtoToGraphic::map)
            //FIX DUPLICATE KEYYS
            .map(g -> this.bind(g, this.gameModel.getWorld()))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    private Pair<MutableObject, GraphicComponent> bind(GraphicComponent g, World world) {
        final Set<MutableObject> str = Stream.concat(this.gameModel.getWorld().obstacles().stream(),
            Stream.of(this.gameModel.getWorld().player())).collect(Collectors.toSet());
        return str.stream()
            .filter(m -> this.joinPredicate(m, g))
            .map(m -> Pair.of(m, g))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }

    
    private Map<MutableObject, GraphicComponent> flattenComponents(Set<Map<MutableObject, GraphicComponent>> sets ) {
        return sets.stream()
            .flatMap(s -> s.entrySet().stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    private boolean joinPredicate(final MutableObject obstacle1, GraphicComponent gComponent) {
        return obstacle1.getDimension().equals(gComponent.dimension()) 
            && obstacle1.getPosition().equals(gComponent.relativePosition());
    }


    @Override
    public void update(final long dt) {
        if(Objects.nonNull(gamePair)) {
            gamePair.forEach((m, g) -> {
                    {
                        // System.out.println(g.absolutePosition());
                        m.updateState(dt);
                        //to fix
                        final Coord2D c = offsetCorrector.correct(g.dimension(), m.getPosition());
                        
                        g.setPosition(c.x(), c.y());
                        g.setDimension(m.getDimension().width(), m.getDimension().height());
                        if(m instanceof PlayerModel) {
                            
                            if(m.getSpeed().xComponent() < 0 && !this.isFlipped) {
                                g.flip();
                                this.isFlipped = true;
                            } else if(m.getSpeed().xComponent() > 0 && this.isFlipped) {
                                g.flip();
                                this.isFlipped = false;
                            }
                            if(g instanceof ImageComponent gr) { 
                                if (m.getSpeed().magnitude() > 0) {
                                    gr.animate(SpriteStatus.RUNNING);
                                } else {
                                    gr.animate(SpriteStatus.IDLE);
                                }
                                
                            }
                        }
                        
                    }
                    
                }
            );
        }
    }

    
}
