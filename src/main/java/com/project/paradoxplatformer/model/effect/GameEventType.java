package com.project.paradoxplatformer.model.effect;

/**
 * Enum representing the different types of game events that can be published
 * by the EventManager. Each type triggers specific events or actions for
 * subscribers to handle.
 */
public enum GameEventType {

    /**
     * Indicates that the view is being initialized.
     */
    INITIALIZE,

    /**
     * Indicates that the view should be stopped.
     */
    STOP_VIEW,

    /**
     * Indicates that the view should be switched to a different one.
     */
    SWITCH_VIEW,

    /**
     * Indicates that the effect handler associated with the collision manager
     * should be updated.
     */
    UPDATE_HANDLER,

    /**
     * Indicates that an object should be removed from the view.
     */
    REMOVE_OBJECT,

    /**
     * Indicates that an trigger has been activated and needs to be processed by
     * effect handler.
     */
    TRIGGER_EFFECT
}
