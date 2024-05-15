package com.project.paradoxplatformer.model;

import com.project.paradoxplatformer.model.player.Player;

public class GameModel {
    private Player player;
    private Level currentLevel;

    public GameModel() {
        player = new Player();
        currentLevel = new Level();
    }

    public Player getPlayer() {
        return player;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public void update(double deltaTime) {
        player.update(deltaTime);
        currentLevel.update(deltaTime);
    }
}
