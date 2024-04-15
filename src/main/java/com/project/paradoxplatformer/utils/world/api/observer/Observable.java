package com.project.paradoxplatformer.utils.world.api.observer;

public interface Observable {
    void addObserver(Observer observer);

    void notifyObservers();
}
