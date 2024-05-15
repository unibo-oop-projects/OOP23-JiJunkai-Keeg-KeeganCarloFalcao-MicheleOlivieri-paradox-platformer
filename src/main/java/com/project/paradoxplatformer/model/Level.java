package com.project.paradoxplatformer.model;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Obstacle> obstacles;

    public Level() {
        obstacles = new ArrayList<>();
        // Initialize obstacles
        obstacles.add(new StaticObstacle(100, 200));
        obstacles.add(new DynamicObstacle(300, 400, 50, 0)); // Example obstacle
    }

    public void update(double deltaTime) {
        for (Obstacle obstacle : obstacles) {
            obstacle.update(deltaTime);
        }
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
}
