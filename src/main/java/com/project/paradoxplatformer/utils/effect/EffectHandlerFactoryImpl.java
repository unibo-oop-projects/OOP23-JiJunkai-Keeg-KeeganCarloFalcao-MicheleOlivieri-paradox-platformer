package com.project.paradoxplatformer.utils.effect;

import java.util.List;

import com.project.paradoxplatformer.utils.collision.ChainOfEffects;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;
import com.project.paradoxplatformer.utils.effect.api.EffectHandlerFactory;
import com.project.paradoxplatformer.utils.effect.api.Level;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.utils.sound.SoundType;

public class EffectHandlerFactoryImpl implements EffectHandlerFactory {

    @Override
    public EffectHandler defaultEffectHandler() {
        EffectHandler handler = new EffectHandler();

        ChainOfEffects chain = ChainOfEffects.create(List.of(
                () -> new NoOpEffect(),
                () -> new SoundEffect(SoundType.OBSTACLE_HIT),
                () -> new TransportEffect(new Coord2D(100, 100), false)));

        handler.addCollisionEffectsForType(CollisionType.TRIGGER, chain);
        handler.addCollisionEffectsForType(CollisionType.DEATH_OBS, DeathEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, SpringEffect::new);
        // handler.addCollisionEffectsForType(CollisionType.SPRINGS, () -> new SoundEffect(SoundType.JUMP));
        handler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
        handler.addCollisionEffectsForType(CollisionType.WALLS, new EffectFactoryImpl()::stoppingEffect);

        return handler;
    }

    @Override
    public EffectHandler getEffectHandlerForLevel(Level level) {
        switch (level) {
            case LEVEL_ONE:
                return levelOneEffectHandler();
            case LEVEL_TWO:
                return levelTwoEffectHandler();
            case LEVEL_THREE:
                return levelThreeEffectHandler();
            case LEVEL_FOUR:
                return levelFourEffectHandler();
            default:
                return defaultEffectHandler();
        }
    }

    private EffectHandler levelOneEffectHandler() {
        EffectHandler handler = new EffectHandler();

        handler.addCollisionEffectsForType(CollisionType.TRIGGER, () -> new ChangeLevelEffect(Level.LEVEL_TWO));
        // handler.addCollisionEffectsForType(CollisionType.DEATH_OBS,
        // DeathEffect::new);
        // handler.addCollisionEffectsForType(CollisionType.SPRINGS, SpringEffect::new);
        // handler.addCollisionEffectsForType(CollisionType.SPRINGS, () -> new
        // SoundEffect(SoundType.JUMP));
        handler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
        handler.addCollisionEffectsForType(CollisionType.WALLS, new EffectFactoryImpl()::stoppingEffect);

        return handler;
    }

    private EffectHandler levelTwoEffectHandler() {
        EffectHandler handler = new EffectHandler();

        handler.addCollisionEffectsForType(CollisionType.TRIGGER, () -> new ChangeLevelEffect(Level.LEVEL_THREE));
        handler.addCollisionEffectsForType(CollisionType.DEATH_OBS, DeathEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, SpringEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, () -> new SoundEffect(SoundType.JUMP));
        handler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
        handler.addCollisionEffectsForType(CollisionType.WALLS, new EffectFactoryImpl()::stoppingEffect);

        return handler;
    }

    private EffectHandler levelThreeEffectHandler() {
        EffectHandler handler = new EffectHandler();

        handler.addCollisionEffectsForType(CollisionType.TRIGGER, () -> new ChangeLevelEffect(Level.LEVEL_FOUR));
        handler.addCollisionEffectsForType(CollisionType.DEATH_OBS, DeathEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, SpringEffect::new);
        handler.addCollisionEffectsForType(CollisionType.SPRINGS, () -> new SoundEffect(SoundType.JUMP));
        handler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
        handler.addCollisionEffectsForType(CollisionType.WALLS, new EffectFactoryImpl()::stoppingEffect);
        handler.addCollisionEffectsForType(CollisionType.PLATFORM, FloorEffect::new );

        return handler;
    }

    private EffectHandler levelFourEffectHandler() {
        EffectHandler handler = new EffectHandler();

        handler.addCollisionEffectsForType(CollisionType.TRIGGER, () -> new ChangeLevelEffect(Level.LEVEL_ONE));
        handler.addCollisionEffectsForType(CollisionType.DEATH_OBS, DeathEffect::new);
        handler.addCollisionEffectsForType(CollisionType.COLLECTING, new EffectFactoryImpl()::collectingEffect);
        handler.addCollisionEffectsForType(CollisionType.WALLS, new EffectFactoryImpl()::stoppingEffect);
        handler.addCollisionEffectsForType(CollisionType.PLATFORM, FloorEffect::new);

        return handler;
    }

}