package com.project.paradoxplatformer.controller.games;

import com.project.paradoxplatformer.controller.deserialization.dtos.GameDTO;
import com.project.paradoxplatformer.model.entity.GameObject;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.obstacles.api.Obstacle;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.world.DataViewMapper;
import com.project.paradoxplatformer.model.world.ModelData;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.ImageComponent;
import com.project.paradoxplatformer.view.fxcomponents.api.GraphicComponent;
import com.project.paradoxplatformer.view.fxcomponents.api.SpriteStatus;
import com.project.paradoxplatformer.view.fxcomponents.containers.api.GraphicContainer;
import com.project.paradoxplatformer.view.fxcomponents.orientations.Offset;
import com.project.paradoxplatformer.view.fxcomponents.orientations.OffsetFactory;
import com.project.paradoxplatformer.view.fxcomponents.orientations.OffsetFactoryImpl;

import javafx.application.Platform;

import java.util.function.Function;
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

    public GameControllerImpl(final ModelData gamemodel, final GraphicContainer gContainer) {
        this.gameModel = gamemodel;
        this.gameView = gContainer;
    }


    @Override
    public void loadModel() {
        this.gameModel.init();
    }

    //Need abstraction for view creation
    @Override
    public void syncView() {
        this.gameView.setDimension(
            this.gameModel.getPackedData().getWidth(),
            this.gameModel.getPackedData().getHeight()
        );

        gamePair = this.flattenComponents(Set.of(
                this.syncGameObjects(g -> !g.getImage().isEmpty(), DataViewMapper.imageToView()::apply),
                this.syncGameObjects(g -> Objects.nonNull(g.getColor()), DataViewMapper.obstacleToModel()::apply)
            )
        );

        OffsetFactory factory = new OffsetFactoryImpl(this.gameView.dimension());
        //renders
        gamePair.entrySet().stream()
            .map(Map.Entry::getValue)//create a factory
            .peek(this.gameView::render)
            .forEach(g -> this.changeOrigin(g, factory.bottomLeft()));
            
        
    }

    private void changeOrigin(GraphicComponent g, Offset offset) {
        Coord2D newOrigin = offset.anchor(g.relativePosition().x(), g.relativePosition().y());
        g.setRelativePositionTo(newOrigin.x(), newOrigin.y(), this.gameView);
    }

    private Map<MutableObject, GraphicComponent> syncGameObjects(final Predicate<GameDTO> objectsRendersPred, final Function<GameDTO, GraphicComponent> dtoToGraphic) {
        return Arrays.stream(this.gameModel.getPackedData().getGameDTOs())
            .filter(objectsRendersPred)
            .map(dtoToGraphic)
            .map(g -> this.bind(g, this.gameModel.getWorld()))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
    }

    private Pair<MutableObject, GraphicComponent> bind(GraphicComponent g, World world) {
        Set<MutableObject> str = Stream.concat(this.gameModel.getWorld().obstacles().stream(),
            Stream.of(this.gameModel.getWorld().player())).collect(Collectors.toSet());
        return str.stream()
            .filter(m -> this.joinPredicate(m, g))
            .map(m -> Pair.of(m, g))
            .findAny()
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
    public void update(long dt) {
        if(Objects.nonNull(gamePair)) {
            gamePair.forEach((m, g) -> {
                    {
                        // System.out.println(g.absolutePosition());
                        m.updateState(dt);
                        //to fix
                        g.setPosition(m.getPosition().x()-gameView.dimension().width()/2, -m.getPosition().y()+gameView.dimension().height()/2);
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
