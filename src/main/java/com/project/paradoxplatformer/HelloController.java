package com.project.paradoxplatformer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import com.project.paradoxplatformer.controller.deserialization.LevelDeserializer;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.controller.games.GameControllerImpl;
import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.model.inputmodel.InputFactory;
import com.project.paradoxplatformer.model.inputmodel.InputFactoryImpl;
import com.project.paradoxplatformer.model.obstacles.api.Obstacle;
import com.project.paradoxplatformer.model.world.ModelData;
import com.project.paradoxplatformer.model.world.PlatfromModelData;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.ImageComponent;
import com.project.paradoxplatformer.view.fxcomponents.containers.GraphicContainerImpl;
import com.project.paradoxplatformer.view.fxcomponents.containers.api.GraphicContainer;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;

public class HelloController {

    @FXML
    private StackPane gamePane;

    private ModelData f;

    @FXML
    public void initialize() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        final LevelDeserializer deserializer = new LevelDeserializer("level1.json");
        LevelDTO level = null;
        try {
            level = deserializer.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        f = new PlatfromModelData(level);
        Objects.requireNonNull(level);
        GraphicContainer g = new GraphicContainerImpl(gamePane);
        GameController gc = new GameControllerImpl(f, g);
        gc.loadModel();
        gc.syncView();

        g.activateKeyInput();
        g.setKeyPressed();
        g.setKeyReleased();
        
        InputFactory imfactory = new InputFactoryImpl(); 
        InputController ic = new InputController(imfactory.advancedModel(), g.getKeyAssetter());
        AnimationTimer a = new AnimationTimer() {

            @Override
            public void handle(long now) {
                ic.inject(f);
                gc.update(25000000L);
            }
        };
        a.start();
    }

    @FXML
    protected void test() {
        //GAME LOOP
        /*
         * update player position
         * update all obstacle position
         * check collision between player and world objects
         * i.e if player is on first ground, its acceleration on y is 0
         */
        
        f.getWorld().obstacles().forEach(Obstacle::effect);
        
    }
}