package com.project.paradoxplatformer.controller.games;

import java.util.Optional;

import com.project.paradoxplatformer.controller.event.EventManager;
import com.project.paradoxplatformer.controller.event.GameEventType;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

/**
 * Manages subscriptions to game events.
 * This class subscribes to various game events and delegates the handling to
 * the provided GameControllerEventListener.
 */
public final class GameControllerEventSubscriber {

    private final GameControllerEventListener gameEventListener;
    private final EventManager<GameEventType, PageIdentifier> eventManager;

    /**
     * Constructs a GameControllerEventSubscriber with the given event listener.
     *
     * @param gameEventListener the listener to handle game events
     */
    public GameControllerEventSubscriber(final GameControllerEventListener gameEventListener) {
        this.gameEventListener = gameEventListener;
        this.eventManager = EventManager.getInstance();
        subscribeToEvents();
    }

    /**
     * Subscribes to various game events and associates them with their handlers.
     */
    private void subscribeToEvents() {
        eventManager.subscribe(GameEventType.STOP_VIEW, this::handleStopView);
        eventManager.subscribe(GameEventType.REMOVE_OBJECT, this::handleRemoveObject);
        eventManager.subscribe(GameEventType.TRIGGER_EFFECT, this::handleTriggerEffect);
        eventManager.subscribe(GameEventType.WIN_CONDITION_MET, this::handleVictory);
    }

    /**
     * Handles the STOP_VIEW event.
     *
     * @param id    the identifier of the page
     * @param level the level associated with the event
     */
    private void handleStopView(final PageIdentifier id, final Level level) {
        gameEventListener.handleStopView(id, level);
    }

    /**
     * Handles the REMOVE_OBJECT event.
     *
     * @param id     the identifier of the page
     * @param object the optional object to be removed
     */
    private void handleRemoveObject(final PageIdentifier id, final Optional<? extends CollidableGameObject> object) {
        gameEventListener.handleRemoveObject(id, object);
    }

    /**
     * Handles the TRIGGER_EFFECT event.
     *
     * @param id    the identifier of the page
     * @param param the obstacle associated with the event
     */
    private void handleTriggerEffect(final PageIdentifier id, final Obstacle param) {
        gameEventListener.handleTriggerEffect(id, param);
    }

    /**
     * Handles the WIN_CONDITION_MET event.
     *
     * @param id    the identifier of the page
     * @param level the level associated with the victory
     */
    private void handleVictory(final PageIdentifier id, final Level level) {
        gameEventListener.handleVictory(id);
    }
}
