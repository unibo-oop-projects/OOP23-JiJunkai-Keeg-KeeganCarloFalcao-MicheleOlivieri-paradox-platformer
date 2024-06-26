package com.project.paradoxplatformer;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.model.inputmodel.InputFactory;
import com.project.paradoxplatformer.model.inputmodel.InputFactoryImpl;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import com.project.paradoxplatformer.view.fxcomponents.RectangleComponent;
import com.project.paradoxplatformer.view.fxcomponents.api.GraphicComponent;
import com.project.paradoxplatformer.view.fxcomponents.containers.GraphicContainerImpl;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class HelloController {

    @FXML
    private StackPane gamePane;

    @FXML
    public void initialize() {

        
        ObjectMapper mapper = new ObjectMapper();
        URL json = HelloController.class.getResource("player.json");
        LevelDTO level = null;
        try {
            if(Objects.isNull(json)) {
                System.out.println("FILE NON TROVATO");
            }
            level = mapper.readValue(json, LevelDTO.class);
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        var item1 = List.of(level.getGameDTOs()).get(1);
        

        var container = new GraphicContainerImpl(gamePane);
        
        container.setDimension(level.getWidth(), level.getHeight());
        GraphicComponent rectangle = new RectangleComponent(
            new Rectangle(),
            new Dimension(item1.getWidth(), item1.getHeight()),
            item1.getColor().toFXColor());
        rectangle.setRelativePositionTo(0, container.dimension().height(), container);
        GraphicComponent player = new RectangleComponent(
            new Rectangle(),
            new Dimension(10, 30),
            Color.BLACK);
        player.setRelativePositionTo(0, container.dimension().height()-rectangle.dimension().height(), container);
        
        container.render(player);
        container.render(rectangle);
        
        PlayerModel pm = new PlayerModel(player.position(), Polar2DVector.nullVector());
        
        container.activateKeyInput();
        container.setKeyPressed();
        container.setKeyReleased();
        InputFactory factory = new InputFactoryImpl();
        InputController inputController = new InputController(factory.advancedModel(), container.getKeyAssetter());
        
        System.out.println(factory.advancedModel().getModel().keySet());
        AnimationTimer a = new AnimationTimer() {

            @Override
            public void handle(long now) {
                inputController.inject(pm);
                pm.updateState(25000000L);
                player.setPosition(pm.getPosition().x(), pm.getPosition().y());
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
        
        
        
    }
}