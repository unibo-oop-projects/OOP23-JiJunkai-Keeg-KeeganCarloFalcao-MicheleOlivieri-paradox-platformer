package com.project.paradoxplatformer.model.effect;

import java.util.List;

import com.project.paradoxplatformer.model.effect.api.Level;
import com.project.paradoxplatformer.model.effect.impl.ChangeLevelEffect;
import com.project.paradoxplatformer.model.effect.impl.DeathEffect;
import com.project.paradoxplatformer.model.effect.impl.FloorEffect;
import com.project.paradoxplatformer.model.effect.impl.NoOpEffect;
import com.project.paradoxplatformer.model.effect.impl.SoundEffect;
import com.project.paradoxplatformer.model.effect.impl.SpringEffect;
import com.project.paradoxplatformer.model.effect.impl.TransportEffect;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.sound.SoundType;

/**
 * Implementation of the EffectHandlerFactory that creates specific
 * EffectHandlers
 * for different levels and default cases. The EffectHandler is responsible for
 * managing
 * the effects applied to game objects during collisions.
 */
public class EffectHandlerFactoryImpl extends AbstractEffectHandlerFactory {

    /**
     * Creates a default EffectHandler with a set of predefined collision effects.
     *
     * @return a default EffectHandler instance
     */
    @Override
    public EffectHandler defaultEffectHandler() {
        EffectHandler handler = new EffectHandler();

        // Create a chain of effects for TRIGGER collision type
        ChainOfEffects chain = ChainOfEffects.create(List.of(
                () -> new NoOpEffect(), // No operation effect
                () -> new SoundEffect(SoundType.OBSTACLE_HIT), // Sound effect for obstacle hit
                () -> new TransportEffect(new Coord2D(100, 100), false) // Transport effect
        ));

        // Add effects to the handler for various collision types
        handler.addCollisionEffectsForType(CollisionType.TRIGGER, chain);
        handler.addCollisionEffectsForType(CollisionType.DEATH_OBS, DeathEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, SpringEffect::new);
        handler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
        handler.addCollisionEffectsForType(CollisionType.WALLS, new EffectFactoryImpl()::stoppingEffect);
        // handler.addCollisionEffectsForType(CollisionType.SPRINGS, () -> new
        // SoundEffect(SoundType.JUMP));

        return handler;
    }

    /**
     * Creates an EffectHandler for level one with specific effects.
     *
     * @return an EffectHandler instance for level one
     */
    private EffectHandler levelOneEffectHandler() {
        EffectHandler handler = new EffectHandler();

        // Add effects specific to level one
        handler.addCollisionEffectsForType(CollisionType.TRIGGER, () -> new ChangeLevelEffect(Level.LEVEL_TWO));
        handler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
        handler.addCollisionEffectsForType(CollisionType.WALLS, new EffectFactoryImpl()::stoppingEffect);
        // handler.addCollisionEffectsForType(CollisionType.DEATH_OBS,
        // DeathEffect::new);
        // handler.addCollisionEffectsForType(CollisionType.SPRINGS, SpringEffect::new);
        // handler.addCollisionEffectsForType(CollisionType.SPRINGS, () -> new
        // SoundEffect(SoundType.JUMP));

        return handler;
    }

    /**
     * Creates an EffectHandler for level two with specific effects.
     *
     * @return an EffectHandler instance for level two
     */
    private EffectHandler levelTwoEffectHandler() {
        EffectHandler handler = new EffectHandler();

        // Add effects specific to level two
        handler.addCollisionEffectsForType(CollisionType.TRIGGER, () -> new ChangeLevelEffect(Level.LEVEL_THREE));
        handler.addCollisionEffectsForType(CollisionType.DEATH_OBS, DeathEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, SpringEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, () -> new SoundEffect(SoundType.JUMP));
        handler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
        handler.addCollisionEffectsForType(CollisionType.WALLS, new EffectFactoryImpl()::stoppingEffect);

        return handler;
    }

    /**
     * Creates an EffectHandler for level three with specific effects.
     *
     * @return an EffectHandler instance for level three
     */
    private EffectHandler levelThreeEffectHandler() {
        EffectHandler handler = new EffectHandler();

        // Add effects specific to level three
        handler.addCollisionEffectsForType(CollisionType.TRIGGER, () -> new ChangeLevelEffect(Level.LEVEL_FOUR));
        handler.addCollisionEffectsForType(CollisionType.DEATH_OBS, DeathEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, SpringEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, () -> new SoundEffect(SoundType.JUMP));
        handler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
        handler.addCollisionEffectsForType(CollisionType.WALLS, new EffectFactoryImpl()::stoppingEffect);
        handler.addCollisionEffectsForType(CollisionType.PLATFORM, FloorEffect::new);

        return handler;
    }

    /**
     * Creates an EffectHandler for level four with specific effects.
     *
     * @return an EffectHandler instance for level four
     */
    private EffectHandler levelFourEffectHandler() {
        EffectHandler handler = new EffectHandler();

        // Add effects specific to level four
        handler.addCollisionEffectsForType(CollisionType.TRIGGER, () -> new ChangeLevelEffect(Level.LEVEL_ONE));
        handler.addCollisionEffectsForType(CollisionType.DEATH_OBS, DeathEffect::new);
        handler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
        handler.addCollisionEffectsForType(CollisionType.WALLS, new EffectFactoryImpl()::stoppingEffect);
        handler.addCollisionEffectsForType(CollisionType.PLATFORM, FloorEffect::new);

        return handler;
    }

    /**
     * Creates an EffectHandler based on the specified level.
     *
     * @param level the level for which to create the EffectHandler
     * @return an EffectHandler instance specific to the level
     */
    @Override
    protected EffectHandler createLevelSpecificHandler(final Level level) {
        return switch (level) {
            case LEVEL_ONE -> levelOneEffectHandler();
            case LEVEL_TWO -> levelTwoEffectHandler();
            case LEVEL_THREE -> levelThreeEffectHandler();
            case LEVEL_FOUR -> levelFourEffectHandler();
            default -> defaultEffectHandler();
        };
    }
}
