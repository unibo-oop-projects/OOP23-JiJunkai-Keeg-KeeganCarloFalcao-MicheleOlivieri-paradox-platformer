package com.project.paradoxplatformer.view.graphics.sprites;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Manages the animation of sprites by selecting the appropriate frame based on
 * the current status.
 * It handles frame indexing and updates based on the number of frames and the
 * minimum frames required for a change.
 *
 * @param <T> The type of sprite image.
 */
public class SpriteAnimator<T> {

    private final Map<SpriteStatus, List<T>> mapSprite;
    private final int minFrames;
    private SpriteStatus prev;
    private int index, frames;

    /**
     * Constructs a new {@code SpriteAnimator} with the given {@code Spriter} and
     * minimum frames.
     *
     * @param spriterSetter The {@code Spriter} providing the sprite images for
     *                      different statuses.
     * @param minFrames     The minimum number of frames required before changing to
     *                      the next frame.
     */
    public SpriteAnimator(final Spriter<T> spriterSetter, final int minFrames) {
        mapSprite = new EnumMap<>(Map.of(
                SpriteStatus.IDLE, spriterSetter.getIdleImage(),
                SpriteStatus.RUNNING, spriterSetter.runningImages(),
                SpriteStatus.JUMPING, spriterSetter.jumpingImages(),
                SpriteStatus.FALLING, spriterSetter.fallingImages()));
        this.minFrames = minFrames;
        this.index = 0;
        this.frames = 0;
        this.prev = SpriteStatus.IDLE; // Default to IDLE status
    }

    /**
     * Selects the appropriate frame for the given {@code SpriteStatus} and applies
     * the action on the selected frame.
     * Updates the frame index based on the number of frames elapsed and the minimum
     * frames required.
     *
     * @param current     The current {@code SpriteStatus} to determine the frame.
     * @param imageAction A {@code Consumer} that accepts the selected sprite image
     *                    for further processing.
     */
    public void selectFrame(final SpriteStatus current, final Consumer<T> imageAction) {
        // Update frame index if the status has not changed
        if (this.prev == current) {
            frames += 1;
            if (frames % minFrames == 0) {
                this.index += 1;
            }
        } else {
            // Reset frame index if the status has changed
            this.index = 0;
            this.frames = 0;
        }

        // Get the sprite list for the current status
        final var inf = mapSprite.get(current);

        // Apply the action on the selected sprite image
        imageAction.accept(inf.get(index % inf.size()));
        this.prev = current;
    }
}
