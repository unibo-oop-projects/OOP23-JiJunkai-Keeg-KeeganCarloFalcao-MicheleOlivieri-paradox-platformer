package com.project.paradoxplatformer;

import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.geometries.vector.Simple2DVector;
import com.project.paradoxplatformer.view.fxcomponents.containers.GraphicContainerImpl;
import com.project.paradoxplatformer.view.player.PlayerView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class HelloController {
    

    @FXML
    private StackPane gamePane;

    @FXML
    protected void onHelloButtonClick() {
        //GAME LOOP
        /*
         * update player position
         * update all obstacle position
         * check collision between player and world objects
         * i.e if player is on first ground, its acceleration on y is 0
         */
        
        var c = new GraphicContainerImpl(gamePane);
        
        
    }
}