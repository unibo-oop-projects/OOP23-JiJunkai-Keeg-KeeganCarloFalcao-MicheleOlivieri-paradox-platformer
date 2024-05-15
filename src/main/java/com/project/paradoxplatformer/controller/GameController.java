package com.project.paradoxplatformer.controller;

import com.project.paradoxplatformer.model.GameModel;
import com.project.paradoxplatformer.view.GameView;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameController {
    private GameModel gameModel;
    private GameView gameView;

    public GameController(GameModel gameModel, GameView gameView, Scene scene) { // Accept the scene as a parameter
        this.gameModel = gameModel;
        this.gameView = gameView;
        setupInputHandling(scene); // Pass the scene to the input handling setup
    }

    public void startGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (lastUpdate > 0) {
                    double deltaTime = (now - lastUpdate) / 1_000_000_000.0;
                    gameModel.update(deltaTime);
                    gameView.update();
                }
                lastUpdate = now;
            }
        };
        gameLoop.start();
    }

    private void setupInputHandling(Scene scene) { // Accept the scene as a parameter
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.LEFT) {
                gameModel.getPlayer().setVelocity(-100, 0);
            } else if (event.getCode() == KeyCode.RIGHT) {
                gameModel.getPlayer().setVelocity(100, 0);
            } else if (event.getCode() == KeyCode.UP) {
                gameModel.getPlayer().setVelocity(0, -100);
            } else if (event.getCode() == KeyCode.DOWN) {
                gameModel.getPlayer().setVelocity(0, 100);
            }
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                gameModel.getPlayer().setVelocity(0, 0);
            } else if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
                gameModel.getPlayer().setVelocity(0, 0);
            }
        });
    }
}
