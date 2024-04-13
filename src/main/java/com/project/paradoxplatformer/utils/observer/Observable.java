package com.project.paradoxplatformer.utils.observer;

public interface Observable {
    void addObserver(Observer observer);

    void notifyObservers();
}
