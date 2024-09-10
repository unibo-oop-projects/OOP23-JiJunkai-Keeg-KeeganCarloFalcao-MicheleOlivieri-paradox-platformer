package com.project.paradoxplatformer.utils.effect.api;

import com.project.paradoxplatformer.utils.Level;
import com.project.paradoxplatformer.utils.effect.EffectHandler;

public interface EffectHandlerFactory {

    EffectHandler defaultEffectHandler();

    EffectHandler getEffectHandlerForLevel(Level level);
}