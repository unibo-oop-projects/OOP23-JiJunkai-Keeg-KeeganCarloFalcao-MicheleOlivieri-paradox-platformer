package com.project.paradoxplatformer.view;

import com.project.paradoxplatformer.model.Obstacle;

import javafx.scene.shape.Rectangle;

public class ObstacleView {
    private Obstacle obstacle;
    private Rectangle node;

    public ObstacleView(Obstacle obstacle) {
        this.obstacle = obstacle;
        this.node = new Rectangle(50, 50); // Customize size and shape as needed
        update();
    }

    public void update() {
        node.setTranslateX(obstacle.getX());
        node.setTranslateY(obstacle.getY());
    }

    public Rectangle getNode() {
        return node;
    }
}
