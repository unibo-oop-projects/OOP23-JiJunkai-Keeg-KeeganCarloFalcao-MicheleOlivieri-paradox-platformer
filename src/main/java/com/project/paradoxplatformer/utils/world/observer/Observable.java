package com.project.paradoxplatformer.utils.world.observer;

public interface Observable {
    void addObserver(Observer observer);

    void notifyObservers();
}
