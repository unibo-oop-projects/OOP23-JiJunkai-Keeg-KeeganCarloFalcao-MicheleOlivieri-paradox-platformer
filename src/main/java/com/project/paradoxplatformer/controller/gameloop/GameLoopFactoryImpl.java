package com.project.paradoxplatformer.controller.gameloop;

import javafx.animation.AnimationTimer;

public class GameLoopFactoryImpl implements TaskLoopFactory{

    private final GameLoop loop;

    public GameLoopFactoryImpl(final GameLoop loop) {
        this.loop = loop;
    }

    @Override
    public LoopManager animationLoop() {
        return new LoopManagerTimer();
    }

    @Override
    public LoopManager threadLoop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'threadLoop'");
    }

    
    private class LoopManagerTimer extends AnimationTimer implements LoopManager{

        private boolean isRunning;

        public LoopManagerTimer() {
            this.isRunning = false;
        }

        @Override
        public void handle(final long now) {
            this.isRunning = true;
            loop.loop(60);
        }

        @Override
        public boolean isRunning() {
            return this.isRunning;
        }

        @Override
        public void stop() {
            if(this.isRunning) {
                super.stop();
                this.isRunning = false;
            }
        }
    
        
    }

    
    
}
