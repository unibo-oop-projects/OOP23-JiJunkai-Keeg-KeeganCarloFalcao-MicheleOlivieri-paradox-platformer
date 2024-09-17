package com.project.paradoxplatformer.model.effect.impl;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.controller.event.EventManager;
import com.project.paradoxplatformer.controller.event.GameEventType;
import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.model.effect.abstracts.AbstractOneTimeEffect;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.view.manager.ViewNavigator;

/**
 * An effect that changes the game level when applied.
 * This effect is a one-time effect, meaning it can only be applied once.
 */
public class ChangeLevelEffect extends AbstractOneTimeEffect {

    private final Level level; // The level to which the game should be changed
    private boolean isNew; //flag to ensure a that the current game is changin in a new level

    /**
     * Constructs a ChangeLevelEffect with the specified level.
     *
     * @param level the level to which the game should be changed
     */
    public ChangeLevelEffect(final Level level) {
        this.level = level;
        isNew = true;
    }

    /**
     * Applies this effect to the specified game object.
     * This implementation triggers a level change by stopping the current view
     * and opening the new view associated with the specified level.
     *
     * @param gameObject the game object to which the effect is applied
     * @return a CompletableFuture that completes when the effect has been applied
     */
    @Override
    protected CompletableFuture<Void> applyToGameObject(final CollidableGameObject gameObject) {
        //To ensure that this operation is done only one time
        if (isNew) {
            // Notify the event manager to stop the current view
            EventManager.getInstance().publish(GameEventType.STOP_VIEW, PageIdentifier.GAME, level);

            // Open the new view associated with the specified level
            ViewNavigator.getInstance().openView(PageIdentifier.GAME, level);
            isNew = false;
        }
        return CompletableFuture.completedFuture(null); // Return a completed future
    }

}
