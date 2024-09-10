package com.project.paradoxplatformer.utils.effect.api;

import com.project.paradoxplatformer.utils.effect.EffectHandler;

public interface EffectHandlerFactory {

    EffectHandler defaultEffectHandler();

    EffectHandler levelOneEffectHandler();

    EffectHandler levelTwoEffectHandler();

    EffectHandler levelThreeEffectHandler();
    
    EffectHandler levelFourEffectHandler();
}