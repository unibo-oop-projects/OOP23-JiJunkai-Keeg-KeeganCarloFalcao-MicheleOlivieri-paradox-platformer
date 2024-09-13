package com.project.paradoxplatformer.model.effect;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;

public interface EffectsFactory {

    Effect collectingEffect();

    Effect transitionEffect(final TrajectoryInfo trajInfo);

    Effect stoppingEffect();
}
