package com.project.paradoxplatformer.controller.gameloop;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.project.paradoxplatformer.utils.geometries.api.observer.Observer;

import javafx.animation.AnimationTimer;

public class GameLoopFactoryImpl implements TaskLoopFactory{

    private final GameLoop loop;
    private static final int SECONDS_TO_MILLIS = 1_000; // millis in a second
    private static final int FPS = 60; // in-game fps
    private static final long PERIOD = SECONDS_TO_MILLIS / FPS;

    public GameLoopFactoryImpl(final GameLoop loop) {
        this.loop = loop;
    }

    @Override
    public ObservableLoopManager animationLoop() {
        return new LoopManagerTimer();
    }

    @Override
    public ObservableLoopManager threadLoop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'threadLoop'");
    }

    private class LoopManagerTimer extends AnimationTimer implements ObservableLoopManager{

        private boolean isRunning;
        private long lastFrame = 0;
        private Set<Observer> observers;

        public LoopManagerTimer() {
            this.isRunning = false;
            this.observers = new HashSet<>();
        }

        @Override
        public void handle(final long now) {
            this.isRunning = true;
            final long delta = lastFrame != 0 ? now - lastFrame : 0;
            this.lastFrame = now;
            final long dt = TimeUnit.NANOSECONDS.toMillis(delta);
            
            loop.loop(dt);
            
            this.delay(dt);
        }

        private void delay(final long dt) {
            
            if (dt < PERIOD) {
                try {
                    Thread.sleep(PERIOD - dt);
                } catch (final InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            }
        }

        @Override
        public boolean isRunning() {
            return this.isRunning;
        }

        @Override
        public void stop() {
            this.notifyObservers();
            if(this.isRunning) {
                super.stop();
                this.isRunning = false;
            }
        }

        @Override
        public void addObserver(Observer observer) {
            this.observers.add(observer);
        }

        @Override
        public void notifyObservers() {
            this.observers.forEach(Observer::update);
        }
    
        
    }

    
    
}
