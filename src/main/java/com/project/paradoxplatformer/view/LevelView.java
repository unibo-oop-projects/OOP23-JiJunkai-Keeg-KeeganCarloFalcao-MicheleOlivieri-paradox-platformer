package com.project.paradoxplatformer.view;

import java.util.ArrayList;
import java.util.List;

import com.project.paradoxplatformer.model.Level;
import com.project.paradoxplatformer.model.Obstacle;

import javafx.scene.Group;

public class LevelView {
    private Level level;
    private Group node;
    private List<ObstacleView> obstacleViews;

    public LevelView(Level level) {
        this.level = level;
        this.node = new Group();
        this.obstacleViews = new ArrayList<>();
        initializeView();
    }

    private void initializeView() {
        for (Obstacle obstacle : level.getObstacles()) {
            ObstacleView obstacleView = new ObstacleView(obstacle);
            obstacleViews.add(obstacleView);
            node.getChildren().add(obstacleView.getNode());
        }
    }

    public void update() {
        for (ObstacleView obstacleView : obstacleViews) {
            obstacleView.update();
        }
    }

    public Group getNode() {
        return node;
    }
}
