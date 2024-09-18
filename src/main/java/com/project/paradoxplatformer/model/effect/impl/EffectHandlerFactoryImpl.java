package com.project.paradoxplatformer.model.effect.impl;

import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.model.effect.api.EffectHandler;
import com.project.paradoxplatformer.model.effect.api.EffectHandlerFactory;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

/**
 * Implementation of the EffectHandlerFactory that creates specific
 * EffectHandlers
 * for different levels and default cases. The EffectHandler is responsible for
 * managing
 * the effects applied to game objects during collisions.
 */
public final class EffectHandlerFactoryImpl implements EffectHandlerFactory {

    private static final int TRANSPORT_EFFECT_X = 300;
    private static final int TRANSPORT_EFFECT_Y = 150;

    /**
     * Creates a default EffectHandler with a set of predefined collision effects.
     *
     * @return a default EffectHandler instance
     */
    private EffectHandler defaultEffectHandler() {
        final EffectHandler handler = new EffectHandlerImpl();

        handler.addCollisionEffectsForType(CollisionType.DEATH_OBS, DeathEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SAW, DeathEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, SpringEffect::new);
        // handler.addCollisionEffectsForType(CollisionType.SPRINGS, () -> new
        // SoundEffect(SoundType.JUMP));
        handler.addCollisionEffectsForType(CollisionType.COLLECTING, CollectingEffect::new);
        handler.addCollisionEffectsForType(CollisionType.WALLS, HorizontalBlockEffect::new);
        handler.addCollisionEffectsForType(CollisionType.PLATFORM, FloorEffect::new);

        return handler;
    }

    /**
     * Creates an EffectHandler for level one with specific effects.
     *
     * @return an EffectHandler instance for level one
     */
    private EffectHandler levelOneEffectHandler() {
        final EffectHandler handler = this.defaultEffectHandler();

        // Add specific effects for level one
         handler.addCollisionEffectsForType(CollisionType.BUTTON, () -> new
         ChangeLevelEffect(Level.LEVEL_TWO));
         handler.addCollisionEffectsForType(CollisionType.SAW, DeathEffect::new);
        return handler;
    }

    /**
     * Creates an EffectHandler for level two with specific effects.
     *
     * @return an EffectHandler instance for level two
     */
    private EffectHandler levelTwoEffectHandler() {
        final EffectHandler handler = this.defaultEffectHandler();

        // Add specific effects for level two
         handler.addCollisionEffectsForType(CollisionType.BUTTON, () -> new
         ChangeLevelEffect(Level.LEVEL_THREE));
        return handler;
    }

    /**
     * Creates an EffectHandler for level three with specific effects.
     *
     * @return an EffectHandler instance for level three
     */
    private EffectHandler levelThreeEffectHandler() {
        final EffectHandler handler = this.defaultEffectHandler();

        // Add specific effects for level three
         handler.addCollisionEffectsForType(CollisionType.BUTTON, () -> new
         ChangeLevelEffect(Level.LEVEL_FOUR));
         handler.addCollisionEffectsForType(CollisionType.WALLS, DeathEffect::new);
        return handler;
    }

    /**
     * Creates an EffectHandler for level four with specific effects.
     *
     * @return an EffectHandler instance for level four
     */
    private EffectHandler levelFourEffectHandler() {
        final EffectHandler handler = this.defaultEffectHandler();

        // Add specific effects for level four
        // handler.addCollisionEffectsForType(CollisionType.BUTTON, () -> new
        // ChangeLevelEffect(Level.LEVEL_ONE));
        handler.addCollisionEffectsForType(CollisionType.BUTTON,
                () -> new TransportEffect(new Coord2D(TRANSPORT_EFFECT_X, TRANSPORT_EFFECT_Y), true));
        return handler;
    }

    /**
     * Creates an EffectHandler based on the specified level.
     *
     * @param level the level for which to create the EffectHandler
     * @return an EffectHandler instance specific to the level
     */
    @Override
    public EffectHandler getEffectHandlerForLevel(final Level level) {
        return switch (level) {
            case LEVEL_ONE -> levelOneEffectHandler();
            case LEVEL_TWO -> levelTwoEffectHandler();
            case LEVEL_THREE -> levelThreeEffectHandler();
            case LEVEL_FOUR -> levelFourEffectHandler();
            default -> defaultEffectHandler();
        };
    }
}
