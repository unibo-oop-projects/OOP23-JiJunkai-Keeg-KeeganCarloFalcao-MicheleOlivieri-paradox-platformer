package com.project.paradoxplatformer.model.effect;

import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.model.effect.impl.ChangeLevelEffect;
import com.project.paradoxplatformer.model.effect.impl.DeathEffect;
import com.project.paradoxplatformer.model.effect.impl.FloorEffect;
import com.project.paradoxplatformer.model.effect.impl.SoundEffect;
import com.project.paradoxplatformer.model.effect.impl.SpringEffect;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.sound.SoundType;

/**
 * Implementation of the EffectHandlerFactory that creates specific
 * EffectHandlers
 * for different levels and default cases. The EffectHandler is responsible for
 * managing
 * the effects applied to game objects during collisions.
 */
public class EffectHandlerFactoryImpl implements EffectHandlerFactory {

    /**
     * Creates a default EffectHandler with a set of predefined collision effects.
     *
     * @return a default EffectHandler instance
     */
    @Override
    public EffectHandler defaultEffectHandler() {
        EffectHandler handler = new EffectHandlerImpl();

        handler.addCollisionEffectsForType(CollisionType.DEATH_OBS, DeathEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, SpringEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, () -> new SoundEffect(SoundType.JUMP));
        handler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
        handler.addCollisionEffectsForType(CollisionType.WALLS, new EffectFactoryImpl()::stoppingEffect);
        handler.addCollisionEffectsForType(CollisionType.PLATFORM, FloorEffect::new);

        return handler;
    }

    /**
     * Creates an EffectHandler for level one with specific effects.
     *
     * @return an EffectHandler instance for level one
     */
    private EffectHandler levelOneEffectHandler() {
        EffectHandler handler = this.defaultEffectHandler();

        // Add specific effects for level one
        handler.addCollisionEffectsForType(CollisionType.BUTTON, () -> new ChangeLevelEffect(Level.LEVEL_TWO));
        return handler;
    }

    /**
     * Creates an EffectHandler for level two with specific effects.
     *
     * @return an EffectHandler instance for level two
     */
    private EffectHandler levelTwoEffectHandler() {
        EffectHandler handler = this.defaultEffectHandler();

        // Add specific effects for level two
        handler.addCollisionEffectsForType(CollisionType.BUTTON, () -> new ChangeLevelEffect(Level.LEVEL_THREE));
        return handler;
    }

    /**
     * Creates an EffectHandler for level three with specific effects.
     *
     * @return an EffectHandler instance for level three
     */
    private EffectHandler levelThreeEffectHandler() {
        EffectHandler handler = this.defaultEffectHandler();

        // Add specific effects for level three
        handler.addCollisionEffectsForType(CollisionType.BUTTON, () -> new ChangeLevelEffect(Level.LEVEL_FOUR));
        return handler;
    }

    /**
     * Creates an EffectHandler for level four with specific effects.
     *
     * @return an EffectHandler instance for level four
     */
    private EffectHandler levelFourEffectHandler() {
        EffectHandler handler = this.defaultEffectHandler();

        // Add specific effects for level four
        handler.addCollisionEffectsForType(CollisionType.BUTTON, () -> new ChangeLevelEffect(Level.LEVEL_ONE));
        return handler;
    }

    /**
     * Creates an EffectHandler based on the specified level.
     *
     * @param level the level for which to create the EffectHandler
     * @return an EffectHandler instance specific to the level
     */
    @Override
    public EffectHandler getEffectHandlerForLevel(Level level) {
        return switch (level) {
            case LEVEL_ONE -> levelOneEffectHandler();
            case LEVEL_TWO -> levelTwoEffectHandler();
            case LEVEL_THREE -> levelThreeEffectHandler();
            case LEVEL_FOUR -> levelFourEffectHandler();
            default -> defaultEffectHandler();
        };
    }
}
