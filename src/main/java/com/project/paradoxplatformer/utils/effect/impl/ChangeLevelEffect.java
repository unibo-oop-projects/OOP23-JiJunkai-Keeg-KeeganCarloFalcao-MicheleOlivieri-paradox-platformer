package com.project.paradoxplatformer.utils.effect.impl;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.EventManager;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.effect.AbstractOneTimeEffect;
import com.project.paradoxplatformer.utils.effect.ViewEventType;
import com.project.paradoxplatformer.utils.effect.api.Level;
import com.project.paradoxplatformer.view.ViewNavigator;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

/**
 * An effect that changes the game level when applied.
 * This effect is a one-time effect, meaning it can only be applied once.
 */
public class ChangeLevelEffect extends AbstractOneTimeEffect {

    private final Level level; // The level to which the game should be changed

    /**
     * Constructs a ChangeLevelEffect with the specified level.
     *
     * @param level the level to which the game should be changed
     */
    public ChangeLevelEffect(Level level) {
        this.level = level;
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
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        try {
            // Notify the event manager to stop the current view
            EventManager.getInstance().publish(ViewEventType.STOP_VIEW, PageIdentifier.GAME, level);

            // Open the new view associated with the specified level
            ViewNavigator.getInstance().openView(PageIdentifier.GAME, level);
        } catch (InvalidResourceException e) {
            e.printStackTrace(); // Print the stack trace if an exception occurs
        }

        return CompletableFuture.completedFuture(null); // Return a completed future
    }

}
