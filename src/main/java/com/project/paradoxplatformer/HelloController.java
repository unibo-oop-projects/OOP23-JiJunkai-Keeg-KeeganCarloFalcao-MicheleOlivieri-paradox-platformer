package com.project.paradoxplatformer;

import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.world.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.world.vector.Simple2DVector;
import com.project.paradoxplatformer.view.player.PlayerView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private ImageView playerView;

    @FXML
    protected void onHelloButtonClick() {
        
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}