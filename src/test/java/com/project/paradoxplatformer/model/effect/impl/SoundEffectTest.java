package com.project.paradoxplatformer.model.effect.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.project.paradoxplatformer.utils.sound.SoundType;

class SoundEffectTest {

    private SoundEffect soundEffect;

    @BeforeEach
    void setUp() {
        final SoundType testSoundType = SoundType.OBSTACLE_HIT;
        soundEffect = new SoundEffect(testSoundType);
    }

    @Test
    void testRecreate() {
        final SoundEffect recreatedEffect = (SoundEffect) soundEffect.recreate();

        assertNotNull(recreatedEffect, "Recreated effect should not be null.");
        assertNotSame(soundEffect, recreatedEffect, "Recreated effect should be a new instance.");
    }
}
