package com.project.paradoxplatformer.model.effect;

import com.project.paradoxplatformer.model.effect.api.Effect;
import com.project.paradoxplatformer.model.entity.TrajectoryInfo;

/**
 * Factory interface for creating various types of effects.
 */
public interface EffectsFactory {

    /**
     * Creates an effect for collecting items.
     * 
     * @return an {@link Effect} for collecting items.
     */
    Effect collectingEffect();

    /**
     * Creates an effect for transitioning based on trajectory information.
     * 
     * @param trajInfo the {@link TrajectoryInfo} to be used for the transition
     *                 effect.
     * @return an {@link Effect} for transition.
     */
    Effect transitionEffect(TrajectoryInfo trajInfo);

    /**
     * Creates an effect for stopping an action or behavior.
     * 
     * @return an {@link Effect} for stopping.
     */
    Effect stoppingEffect();
}
