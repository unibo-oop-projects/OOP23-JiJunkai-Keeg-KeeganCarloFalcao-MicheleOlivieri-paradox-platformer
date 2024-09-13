package com.project.paradoxplatformer.utils.effect;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.EventManager;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.effect.api.Level;
import com.project.paradoxplatformer.view.ViewNavigator;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

public class ChangeLevelEffect extends AbstractOneTimeEffect {

    private final Level level;

    public ChangeLevelEffect(Level level) {
        this.level = level;
    }

    @Override
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        try {
            EventManager.getInstance().publish(ViewEventType.STOP_VIEW, PageIdentifier.GAME, level);
            ViewNavigator.getInstance().openView(PageIdentifier.GAME, level);
        } catch (InvalidResourceException e) {
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(null);
    }
}
