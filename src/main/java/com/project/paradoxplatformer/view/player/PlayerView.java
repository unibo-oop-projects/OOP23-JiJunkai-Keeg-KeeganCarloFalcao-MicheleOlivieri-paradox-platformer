package com.project.paradoxplatformer.view.player;

import com.project.paradoxplatformer.model.player.Player;

import javafx.scene.shape.Rectangle;

public class PlayerView {
    private Player player;
    private Rectangle node;

    public PlayerView(Player player) {
        this.player = player;
        this.node = new Rectangle(50, 50);
        update();
    }

    public void update() {
        node.setTranslateX(player.getX());
        node.setTranslateY(player.getY());
    }

    public Rectangle getNode() {
        return node;
    }
}
