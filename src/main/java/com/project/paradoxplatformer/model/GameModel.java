package com.project.paradoxplatformer.model;

import com.project.paradoxplatformer.model.player.Player;
import com.project.paradoxplatformer.utils.world.Tile;
import com.project.paradoxplatformer.utils.world.TileMap;

public class GameModel {
    private Player player;
    private Level currentLevel;
    private TileMap tileMap;

    public GameModel() {
        player = new Player();
        currentLevel = new Level();

        tileMap = new TileMap(10, 5); // Example size, adjust as needed

        // Example: Set some tiles as solid
        tileMap.setTile(3, 2, new Tile(true));
        tileMap.setTile(4, 2, new Tile(true));
        tileMap.setTile(5, 2, new Tile(true));
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

    public TileMap getTileMap() {
        return tileMap;
    }
}
