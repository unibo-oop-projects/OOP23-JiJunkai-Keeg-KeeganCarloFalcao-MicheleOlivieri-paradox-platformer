package com.project.paradoxplatformer.model.effect.impl;

import com.project.paradoxplatformer.utils.sound.SoundType;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SoundEffectTest {

    private SoundEffect soundEffect;
    private SoundType testSoundType;

    @BeforeEach
    void setUp() {
        testSoundType = SoundType.JUMP;
        soundEffect = new SoundEffect(testSoundType);
    }

    @Test
    @SuppressFBWarnings(value = "UwF", justification = "Fields are initialized in @BeforeEach method before usage.")
    void testRecreate() {
        SoundEffect recreatedEffect = (SoundEffect) soundEffect.recreate();

        assertNotNull(recreatedEffect, "Recreated effect should not be null.");
        assertNotSame(soundEffect, recreatedEffect, "Recreated effect should be a new instance.");
    }
}
