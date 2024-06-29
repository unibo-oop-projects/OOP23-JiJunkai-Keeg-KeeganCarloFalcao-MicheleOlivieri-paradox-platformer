package com.project.paradoxplatformer.model.timer.api;

import com.project.paradoxplatformer.utils.geometries.api.observer.Observable;

public interface Timer extends Observable {

    void startTimer();

    void stopTimer();

    long getTimeElapsed();

}
