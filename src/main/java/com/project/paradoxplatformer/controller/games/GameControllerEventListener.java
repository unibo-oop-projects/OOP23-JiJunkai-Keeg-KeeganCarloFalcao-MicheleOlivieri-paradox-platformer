package com.project.paradoxplatformer.controller.games;

import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

import java.util.Optional;

/**
 * Interface for handling game controller events.
 * Implementations of this interface will handle various game events such as
 * stopping the view, removing objects, triggering effects, and handling victory
 * conditions.
 */
public interface GameControllerEventListener {

    /**
     * Handles the STOP_VIEW event.
     *
     * @param id    the identifier of the page
     * @param param the level associated with the event
     */
    void handleStopView(PageIdentifier id, Level param);

    /**
     * Handles the REMOVE_OBJECT event.
     *
     * @param id     the identifier of the page
     * @param object the optional object to be removed
     */
    void handleRemoveObject(PageIdentifier id, Optional<? extends CollidableGameObject> object);

    /**
     * Handles the TRIGGER_EFFECT event.
     *
     * @param id    the identifier of the page
     * @param param the obstacle associated with the event
     */
    void handleTriggerEffect(PageIdentifier id, Obstacle param);

    /**
     * Handles the WIN_CONDITION_MET event.
     *
     * @param id the identifier of the page
     */
    void handleVictory(PageIdentifier id, Level level);
}
