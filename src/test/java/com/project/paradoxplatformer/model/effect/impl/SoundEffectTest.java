package com.project.paradoxplatformer.model.effect.impl;

import com.project.paradoxplatformer.model.effect.impl.SoundEffect;
import com.project.paradoxplatformer.utils.sound.SoundType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoundEffectTest {

    private SoundEffect soundEffect;
    private SoundType testSoundType;

    @BeforeEach
    void setUp() {
        testSoundType = SoundType.JUMP;
        soundEffect = new SoundEffect(testSoundType);
    }

    @Test
    void testRecreate_CreatesNewSoundEffect() {
        SoundEffect recreatedEffect = (SoundEffect) soundEffect.recreate();

        assertNotNull(recreatedEffect, "Recreated effect should not be null.");
        assertNotSame(soundEffect, recreatedEffect, "Recreated effect should be a new instance.");
    }
}
