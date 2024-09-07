package com.project.paradoxplatformer.model.ui;

import com.project.paradoxplatformer.model.ui.api.Scoreboard;

public class GameScoreboard implements Scoreboard {
    private long timePassed;
    private int deathCounter;

    @Override
    public void updateTimePassed(long time) {
        this.timePassed = time;
    }

    @Override
    public void incrementDeathCounter() {
        this.deathCounter++;
    }

    @Override
    public void display() {
        System.out.println("Time Passed: " + timePassed + " seconds");
        System.out.println("Deaths: " + deathCounter);
    }
}
