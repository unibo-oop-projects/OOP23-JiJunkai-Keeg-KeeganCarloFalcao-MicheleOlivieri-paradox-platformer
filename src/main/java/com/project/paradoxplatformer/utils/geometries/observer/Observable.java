package com.project.paradoxplatformer.utils.geometries.observer;

/**
 * Represents an observable entity in the observer design pattern.
 * <p>
 * The observable is a subject that maintains a list of observers and notifies
 * them
 * when there are changes or updates. It provides methods to add observers and
 * to
 * notify all registered observers of any updates.
 * </p>
 */
public interface Observable {

    /**
     * Adds an observer to the list of observers.
     * <p>
     * This method allows an observer to register itself with the observable
     * so that it can be notified of any updates or changes.
     * </p>
     *
     * @param observer the observer to be added
     */
    void addObserver(Observer observer);

    /**
     * Notifies all registered observers of any changes or updates.
     * <p>
     * This method triggers the update method on each observer, allowing them
     * to react to the changes in the observable.
     * </p>
     */
    void notifyObservers();
}
