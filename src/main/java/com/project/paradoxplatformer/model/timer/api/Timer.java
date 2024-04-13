package com.project.paradoxplatformer.model.timer.api;

import com.project.paradoxplatformer.utils.observer.Observable;

public interface Timer extends Observable {

    void startTimer();

    void stopTimer();

    long getTimeElapsed();

}
