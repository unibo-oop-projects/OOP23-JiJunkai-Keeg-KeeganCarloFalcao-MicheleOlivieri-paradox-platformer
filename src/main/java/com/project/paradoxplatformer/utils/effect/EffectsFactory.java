package com.project.paradoxplatformer.utils.effect;

import com.project.paradoxplatformer.model.entity.TrajectoryInfo;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.effect.api.Effect;

public interface EffectsFactory {
    
    Effect collectingEffect(final PlayerModel player);

    Effect transitionEffect(final TrajectoryInfo trajInfo);
}
