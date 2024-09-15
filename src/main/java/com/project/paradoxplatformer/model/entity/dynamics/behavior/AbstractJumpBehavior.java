package com.project.paradoxplatformer.model.entity.dynamics.behavior;

public abstract class AbstractJumpBehavior implements JumpBehavior{

    private boolean isFalling = true;

    @Override
    public void setFalling(boolean falling) {
        this.isFalling = falling;
    }

    @Override
    public boolean isFalling() {
        return isFalling;
    }

}
