package com.project.paradoxplatformer.model.counter;

import java.util.ArrayList;
import java.util.List;

import com.project.paradoxplatformer.model.counter.api.Counter;
import com.project.paradoxplatformer.utils.observer.Observable;
import com.project.paradoxplatformer.utils.world.observer.Observer;

public class DeathCounterModel implements Counter, Observable {

    private int deathCount;

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void incrementCount() {
        deathCount++;
        notifyObservers();
    }

    @Override
    public void resetCount() {
        deathCount = 0;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public int getDeathCounter() {
        return deathCount;
    }

}
