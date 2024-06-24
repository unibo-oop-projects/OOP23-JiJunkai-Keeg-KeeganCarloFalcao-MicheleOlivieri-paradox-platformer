package com.project.paradoxplatformer;

import com.project.paradoxplatformer.controller.player.PlayerController;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.Polar2DVector;
import com.project.paradoxplatformer.utils.geometries.vector.Simple2DVector;
import com.project.paradoxplatformer.view.fxcomponents.GraphicComponent;
import com.project.paradoxplatformer.view.fxcomponents.RectangleComponent;
import com.project.paradoxplatformer.view.fxcomponents.containers.GraphicContainerImpl;
import com.project.paradoxplatformer.view.player.PlayerView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class HelloController {

    @FXML
    private StackPane gamePane;

    @FXML
    public void initialize() {
        var container = new GraphicContainerImpl(gamePane);
        container.setDimension(200, 200);
        GraphicComponent rectangle = new RectangleComponent(
            new Rectangle(),
            new Dimension(200, 50),
            Color.DARKBLUE);
        rectangle.setRelativePositionTo(0, container.dimension().height(), container);
        GraphicComponent player = new RectangleComponent(
            new Rectangle(),
            new Dimension(30, 30),
            Color.BLACK);
        player.setRelativePositionTo(0, container.dimension().height()-rectangle.dimension().height(), container);
        
        container.render(player);
        container.render(rectangle);

        
        PlayerModel pm = new PlayerModel(player.position(), Polar2DVector.nullVector());
        
        gamePane.requestFocus();
        gamePane.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.D) {
                pm.moveRight();
                pm.updateState(25000000L);
                player.setPosition(pm.getPosition().x(), pm.getPosition().y());
            }

            if(e.getCode() == KeyCode.A) {
                pm.moveLeft();
                pm.updateState(25000000L);
                player.setPosition(pm.getPosition().x(), pm.getPosition().y());
            }
        });
        gamePane.setOnKeyReleased(e -> {
            pm.stop();
            player.setPosition(pm.getPosition().x(), pm.getPosition().y());
        });
        
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