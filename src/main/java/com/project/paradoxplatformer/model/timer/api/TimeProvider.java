package com.project.paradoxplatformer.model.timer.api;

public interface TimeProvider {
    long getCurrentTime();

    void adjustSpeed(double speedMultiplier);
}
