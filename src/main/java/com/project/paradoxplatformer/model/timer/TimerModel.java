package com.project.paradoxplatformer.model.timer;

import java.util.ArrayList;
import java.util.List;

import com.project.paradoxplatformer.model.timer.api.Timer;
import com.project.paradoxplatformer.utils.world.observer.Observer;

/**
 * TimerModel
 */
public class TimerModel implements Timer {

    private long startTime;
    private long endTime;
    private boolean running;
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public void startTimer() {
        startTime = System.currentTimeMillis();
        running = true;
        notifyObservers();

    }

    @Override
    public void stopTimer() {
        endTime = System.currentTimeMillis();
        running = false;
        notifyObservers();
    }

    @Override
    public long getTimeElapsed() {
        if (running) {
            return System.currentTimeMillis() - startTime;
        } else {
            return endTime - startTime;
        }
    }

}
