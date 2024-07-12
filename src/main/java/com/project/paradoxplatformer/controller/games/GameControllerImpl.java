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
import com.project.paradoxplatformer.view.fxcomponents.FXImageAdapter;
import com.project.paradoxplatformer.view.game.GameView;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;
import com.project.paradoxplatformer.view.graphics.sprites.SpriteStatus;

import java.util.Objects;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

public class GameControllerImpl implements GameController{

    private final ModelData gameModel;
    private Map<MutableObject, GraphicAdapter> gamePair;
    private GameView gameView;

    public GameControllerImpl(final ModelData model, final GameView view) {
        this.gameModel = model;
        this.gameView = view;
        this.gamePair = new HashMap<>();
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
        
        
            
    }

    private Pair<MutableObject, GraphicAdapter> bind(GraphicAdapter g, World world) {
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


    private boolean joinPredicate(final MutableObject obstacle1, GraphicAdapter gComponent) {
        return obstacle1.getDimension().equals(gComponent.dimension()) 
            && obstacle1.getPosition().equals(gComponent.relativePosition());
    }

    @Override
    public void update(final long dt) {
        if(Objects.nonNull(gamePair)) {
            gamePair.forEach((m, g) -> m.updateState(dt));
            gamePair.forEach(gameView::updateEnitityState);
        }
    }

    
}
