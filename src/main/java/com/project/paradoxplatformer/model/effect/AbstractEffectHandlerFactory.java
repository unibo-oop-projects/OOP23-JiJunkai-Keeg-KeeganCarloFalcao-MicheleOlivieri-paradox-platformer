package com.project.paradoxplatformer.model.effect;

import com.project.paradoxplatformer.model.effect.api.EffectHandlerFactory;
import com.project.paradoxplatformer.model.effect.api.Level;
import com.project.paradoxplatformer.model.effect.impl.FloorEffect;
import com.project.paradoxplatformer.utils.collision.api.CollisionType;

public abstract class AbstractEffectHandlerFactory implements EffectHandlerFactory {

    // Metodo che aggiunge il FloorEffect a tutti i livelli
    protected void addCommonEffects(EffectHandler handler) {
        handler.addCollisionEffectsForType(CollisionType.PLATFORM, FloorEffect::new);
    }

    // Ogni sottoclasse dovrà implementare i metodi di gestione specifici per ogni
    // livello
    protected abstract EffectHandler createLevelSpecificHandler(Level level);

    @Override
    public EffectHandler getEffectHandlerForLevel(Level level) {
        EffectHandler handler = createLevelSpecificHandler(level);
        addCommonEffects(handler); // Aggiunge gli effetti comuni (es. FloorEffect)
        return handler;
    }

}
