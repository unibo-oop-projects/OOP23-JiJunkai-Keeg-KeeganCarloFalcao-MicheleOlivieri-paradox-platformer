package com.project.paradoxplatformer.controller;

import com.project.paradoxplatformer.model.GameModel;
import com.project.paradoxplatformer.model.player.Player;
import com.project.paradoxplatformer.view.GameView;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Scene;

public class GameController {
    private GameModel gameModel;
    private GameView gameView;

    public GameController(GameModel gameModel, GameView gameView, Scene scene) {
        this.gameModel = gameModel;
        this.gameView = gameView;
        setupInputHandling(scene);
    }

    public void startGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                if (lastUpdate > 0) {
                    double deltaTime = (now - lastUpdate) / 1_000_000_000.0;
                    gameModel.update(deltaTime);
                    handlePlayerJump(); // Handle player jumping
                    gameView.update();
                }
                lastUpdate = now;
            }
        };
        gameLoop.start();
    }

    private void setupInputHandling(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case LEFT -> gameModel.getPlayer().setVelocity(-100,
                        gameModel.getPlayer().isJumping() ? gameModel.getPlayer().getVelocityY() : 0);
                case RIGHT -> gameModel.getPlayer().setVelocity(100,
                        gameModel.getPlayer().isJumping() ? gameModel.getPlayer().getVelocityY() : 0);
                case UP -> {
                    gameModel.getPlayer().jump();
                    gameModel.getPlayer().setVelocity(gameModel.getPlayer().getVelocityX(), 0);
                }
                case DOWN -> gameModel.getPlayer().setVelocity(gameModel.getPlayer().getVelocityX(), 100);
            }
        });

        scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.RIGHT) {
                gameModel.getPlayer().setVelocity(0, gameModel.getPlayer().getVelocityY());
            } else if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
                gameModel.getPlayer().setVelocity(gameModel.getPlayer().getVelocityX(), 0);
            }
        });
    }

    private void handlePlayerJump() {
        if (gameModel.getPlayer().getY() == Player.GROUND_LEVEL && gameModel.getPlayer().isJumping()) {
            gameModel.getPlayer().setVelocity(gameModel.getPlayer().getVelocityX(), 0);
        }
    }
}
