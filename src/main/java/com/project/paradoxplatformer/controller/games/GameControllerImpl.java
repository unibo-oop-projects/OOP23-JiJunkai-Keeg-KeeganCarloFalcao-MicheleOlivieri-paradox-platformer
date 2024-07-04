package com.project.paradoxplatformer.controller.games;

import com.project.paradoxplatformer.Views;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.model.world.ModelData;
import com.project.paradoxplatformer.model.world.api.World;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.orientations.GraphicOffsetCorrector;
import com.project.paradoxplatformer.utils.geometries.orientations.OffsetCorrector;
import com.project.paradoxplatformer.utils.geometries.orientations.factory.OffsetFactory;
import com.project.paradoxplatformer.utils.geometries.orientations.factory.OffsetFactoryImpl;
import com.project.paradoxplatformer.utils.geometries.vector.Simple2DVector;
import com.project.paradoxplatformer.view.fxcomponents.ImageComponent;
import com.project.paradoxplatformer.view.fxcomponents.api.GraphicComponent;
import com.project.paradoxplatformer.view.fxcomponents.api.SpriteStatus;
import com.project.paradoxplatformer.view.game.GameView;

import java.util.Objects;
import java.util.Set;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

public class GameControllerImpl implements GameController{

    private final ModelData gameModel;
    private Map<MutableObject, GraphicComponent> gamePair;
    private boolean isFlipped;
    private OffsetCorrector offsetCorrector;
    private GameView gameView;

    public GameControllerImpl(final ModelData model, final GameView view) {
        this.gameModel = model;
        this.gameView = view;
        //SHOULD GET FROM VIEW ACTUALLY
    }

    @Override
    public void loadModel() {
        gameModel.init();
    }

    //Need abstraction for view creation
    @Override
    public void syncView() {
        
        this.gameView.init();
        gamePair = this.gameView.getControls().stream()
            //FIX DUPLICATE KEYYS
            .map(g -> this.bind(g, this.gameModel.getWorld()))
            .collect(Collectors.toMap(Pair::getKey, Pair::getValue));

        //PROB DO IN VIEW UPDATE
        //CONFIRMED BY SPOTBUGS; ITS DEREFENCED
        final OffsetFactory factory = new OffsetFactoryImpl(this.gameView.dimension());
        this.offsetCorrector = new GraphicOffsetCorrector(
            factory.bottomLeft(), //BETTER SEPARATE LAYOUT AND BOX IN FACTORY, MAKE A LIST
            factory.boxOffset(),// SO CAN USE REDUCE IN IMPLEMENTATIOn
            new Simple2DVector(1, -1)
        );
        
            
    }

    private Pair<MutableObject, GraphicComponent> bind(GraphicComponent g, World world) {
        //SHOULD GET FROM WORLD, JUST TO MAKE THINGS EASY
        //MAKE A CONCAT OF ALL ENTITIES
        final Set<MutableObject> str = Stream.concat(this.gameModel.getWorld().obstacles().stream(),
            Stream.of(this.gameModel.getWorld().player())).collect(Collectors.toSet());
        return str.stream()
            .filter(m -> this.joinPredicate(m, g))
            .map(m -> Pair.of(m, g))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }


    private boolean joinPredicate(final MutableObject obstacle1, GraphicComponent gComponent) {
        return obstacle1.getDimension().equals(gComponent.dimension()) 
            && obstacle1.getPosition().equals(gComponent.relativePosition());
    }

    @Override
    public void update(final long dt) {
        if(Objects.nonNull(gamePair)) {
            //TO FIX; TOO BULKY
            gamePair.forEach((m, g) -> {
                    {
                        // System.out.println(g.absolutePosition());
                        m.updateState(dt);
                        //to fix
                        final Coord2D c = offsetCorrector.correct(g.dimension(), m.getPosition());
                        
                        g.setPosition(c.x() * Views.SIZE_FACTOR, c.y() * Views.SIZE_FACTOR);
                        
                        g.setDimension(m.getDimension().width() * Views.SIZE_FACTOR, m.getDimension().height() * Views.SIZE_FACTOR);
                        if(m instanceof PlayerModel pl) {
                            //JUST FOR TESTING, MUST DO BETTER
                            if(pl.getSp().x() < 0 && !this.isFlipped) {
                                g.flip();
                                this.isFlipped = true;
                            } else if(pl.getSp().x() > 0 && this.isFlipped) {
                                g.flip();
                                this.isFlipped = false;
                            }
                            if(g instanceof ImageComponent gr) { 
                                if (pl.getSp().module() > 0) {
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
