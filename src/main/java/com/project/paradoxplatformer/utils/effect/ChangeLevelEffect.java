package com.project.paradoxplatformer.utils.effect;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.utils.EventManager;
import com.project.paradoxplatformer.utils.EventType;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.utils.Level;
import com.project.paradoxplatformer.utils.LevelSwitcher;

public class ChangeLevelEffect extends AbstractOneTimeEffect {

    private final Level gameLevel;
    private final LevelSwitcher levelSwitcher;

    public ChangeLevelEffect(Level gameLevel, LevelSwitcher levelSwitcher) {
        this.gameLevel = gameLevel;
        this.levelSwitcher = levelSwitcher;
    }

    @Override
    protected CompletableFuture<Void> applyToGameObject(CollidableGameObject gameObject) {
        return CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Switching to level: " + gameLevel);
                levelSwitcher.switchLevel(gameLevel);

                EventManager.getInstance().publish(EventType.EFFECT_HANDLER_UPDATED,
                        new EffectHandlerFactoryImpl().defaultEffectHandler(), null);

            } catch (InvalidResourceException e) {
                e.printStackTrace();
            }
        });
    }
}
