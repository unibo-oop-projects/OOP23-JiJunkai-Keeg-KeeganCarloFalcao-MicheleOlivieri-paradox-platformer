package com.project.paradoxplatformer.utils.geometries.observer;

/**
 * Represents an observer in the observer design pattern.
 * <p>
 * The observer is an entity that listens for updates or changes in a subject
 * and reacts to these changes when notified. Implementing this interface
 * allows objects to be notified when the subject they are observing is updated.
 * </p>
 */
public interface Observer {

    /**
     * Updates the observer with new information or changes from the subject.
     * <p>
     * This method is called by the subject to notify the observer of any
     * changes or updates. The specific details of the update should be handled
     * by the implementation of this method.
     * </p>
     */
    void update();
}
