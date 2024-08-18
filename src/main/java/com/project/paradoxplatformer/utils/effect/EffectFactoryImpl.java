package com.project.paradoxplatformer.utils.effect;

import java.util.concurrent.CompletableFuture;

import com.project.paradoxplatformer.model.obstacles.Coin;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.effect.api.Effect;

public class EffectFactoryImpl implements EffectsFactory {

    @Override
    public Effect collectingEffect(final PlayerModel p) {
        return t -> CompletableFuture.runAsync(() -> {
            t.filter(Coin.class::isInstance).ifPresent(c -> p.collectCoin());
        });
    }

    @Override
    public Effect transitionEffect() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'movingEffect'");
    }
    
}
