package com.project.paradoxplatformer.view;

import com.project.paradoxplatformer.model.GameModel;
import com.project.paradoxplatformer.utils.world.Tile;
import com.project.paradoxplatformer.utils.world.TileMap;
import com.project.paradoxplatformer.view.player.PlayerView;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameView {
    private GameModel gameModel;
    private Group root;
    private PlayerView playerView;
    private LevelView levelView;

    public GameView(GameModel gameModel) {
        this.gameModel = gameModel;
        this.root = new Group();
        initializeView();
        render();
    }

    private void initializeView() {
        playerView = new PlayerView(gameModel.getPlayer());
        root.getChildren().add(playerView.getNode());

        levelView = new LevelView(gameModel.getCurrentLevel());
        root.getChildren().add(levelView.getNode());
    }

    public Group getRoot() {
        return root;
    }

    private void render() {
        TileMap tileMap = gameModel.getTileMap();

        for (int x = 0; x < tileMap.getWidth(); x++) {
            for (int y = 0; y < tileMap.getHeight(); y++) {
                Tile tile = tileMap.getTile(x, y);
                if (tile != null && tile.isSolid()) {
                    Rectangle tileRect = new Rectangle(x * 50, y * 50, 50, 50); // Adjust size as needed
                    tileRect.setFill(Color.BLACK); // Example color
                    root.getChildren().add(tileRect);
                }
            }
        }
    }

    public void update() {
        playerView.update();
        levelView.update();
    }
}
