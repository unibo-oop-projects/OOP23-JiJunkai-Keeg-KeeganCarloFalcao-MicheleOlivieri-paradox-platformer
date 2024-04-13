package com.project.paradoxplatformer.utils.observer;

import com.project.paradoxplatformer.utils.world.observer.Observer;

public interface Observable {
    void addObserver(Observer observer);

    void notifyObservers();
}
