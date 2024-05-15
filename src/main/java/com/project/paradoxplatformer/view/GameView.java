package com.project.paradoxplatformer.view;

import com.project.paradoxplatformer.model.GameModel;
import com.project.paradoxplatformer.view.player.PlayerView;

import javafx.scene.Group;

public class GameView {
    private GameModel gameModel;
    private Group root;
    private PlayerView playerView;
    private LevelView levelView;

    public GameView(GameModel gameModel) {
        this.gameModel = gameModel;
        this.root = new Group();
        initializeView();
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

    public void update() {
        playerView.update();
        levelView.update();
    }
}
