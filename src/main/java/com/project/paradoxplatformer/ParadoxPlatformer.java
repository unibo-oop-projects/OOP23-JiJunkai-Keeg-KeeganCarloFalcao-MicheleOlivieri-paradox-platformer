package com.project.paradoxplatformer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.project.paradoxplatformer.controller.GameController;
import com.project.paradoxplatformer.model.GameModel;
import com.project.paradoxplatformer.view.GameView;

public class ParadoxPlatformer extends Application {

    private GameModel gameModel;
    private GameView gameView;
    private GameController gameController;

    @Override
    public void start(Stage primaryStage) {
        gameModel = new GameModel();
        gameView = new GameView(gameModel);

        Scene scene = new Scene(gameView.getRoot(), 800, 600);
        gameController = new GameController(gameModel, gameView, scene); // Pass the scene to the controller

        primaryStage.setScene(scene);
        primaryStage.setTitle("Paradox Platformer");
        primaryStage.show();

        gameController.startGameLoop();
    }

    public static void main(String[] args) {
        launch();
    }
}