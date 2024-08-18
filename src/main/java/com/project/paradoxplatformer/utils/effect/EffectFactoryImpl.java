package com.project.paradoxplatformer.utils.effect;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.model.obstacles.CollectableGameObject;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.effect.api.Effect;

public class EffectFactoryImpl implements EffectsFactory {

    @Override
    public Effect collectingEffect(final PlayerModel player) {
        return t -> CompletableFuture.runAsync(() -> {
            t.filter(CollectableGameObject.class::isInstance)
                .map(CollectableGameObject.class::cast)
                .ifPresent(player::collectCoin);
        });
    }

    @Override
    public Effect transitionEffect() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'movingEffect'");
    }
    
}
