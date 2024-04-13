package com.project.paradoxplatformer.model.timer;

import com.project.paradoxplatformer.model.timer.api.TimeProvider;

public class TimeProviderModel implements TimeProvider {

    private double speedMultiplier;

    public TimeProviderModel() {
        this.speedMultiplier = 1.0;
    }

    public TimeProviderModel(double initialSpeedMultiplier) {
        this.speedMultiplier = initialSpeedMultiplier;
    }

    @Override
    public long getCurrentTime() {
        return (long) (System.currentTimeMillis() * speedMultiplier);
    }

    @Override
    public void adjustSpeed(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
    }

}
