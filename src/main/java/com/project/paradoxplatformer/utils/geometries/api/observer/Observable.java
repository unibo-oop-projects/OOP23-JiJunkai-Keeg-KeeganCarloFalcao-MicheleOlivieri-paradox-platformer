package com.project.paradoxplatformer.utils.geometries.api.observer;

public interface Observable {
    void addObserver(Observer observer);

    void notifyObservers();
}
