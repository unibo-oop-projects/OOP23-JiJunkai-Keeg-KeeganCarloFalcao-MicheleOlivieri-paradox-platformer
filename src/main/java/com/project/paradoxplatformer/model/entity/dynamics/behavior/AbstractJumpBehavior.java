package com.project.paradoxplatformer.model.entity.dynamics.behavior;

/**
 * Abstract class providing a base implementation for jump behaviors.
 * <p>This class manages the state of falling and provides basic implementation 
 * for the JumpBehavior interface methods. Subclasses should provide specific 
 * implementations for jumping and falling logic.</p>
 */
public abstract class AbstractJumpBehavior implements JumpBehavior {

    private boolean isFalling = true;

    /**
     * Sets the falling state of the object.
     * @param falling true if the object is falling, false otherwise.
     */
    @Override
    public void setFalling(final boolean falling) {
        this.isFalling = falling;
    }

    /**
     * Checks if the object is currently falling.
     * @return true if the object is falling, false otherwise.
     */
    @Override
    public boolean isFalling() {
        return isFalling;
    }
}
