package com.project.paradoxplatformer.model.ui.api;

public interface Scoreboard {
    void updateTimePassed(long time);

    void incrementDeathCounter();

    void display();
}