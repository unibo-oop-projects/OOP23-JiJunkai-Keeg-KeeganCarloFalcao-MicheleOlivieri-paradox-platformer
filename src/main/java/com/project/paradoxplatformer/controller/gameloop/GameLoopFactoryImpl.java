package com.project.paradoxplatformer.controller.gameloop;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.text.ViewFactory;

import com.project.paradoxplatformer.controller.ExceptionUtils;
import com.project.paradoxplatformer.utils.geometries.api.observer.Observer;
import com.project.paradoxplatformer.view.legacy.ViewLegacy;

import javafx.animation.AnimationTimer;

/**
 * implrmentation.
 */
public class GameLoopFactoryImpl implements TaskLoopFactory {

    private final GameLoop loop;
    private static final int SECONDS_TO_MILLIS = 1_000; // millis in a second
    private static final int FPS = 50; // in-game fps
    private static final long PERIOD = SECONDS_TO_MILLIS / FPS;

    /**
     * Constructor.
     * @param loop the game loop
     */
    public GameLoopFactoryImpl(final GameLoop loop) {
        this.loop = loop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableLoopManager animationLoop() {
        return new LoopManagerTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ObservableLoopManager threadLoop() {
        return new ObservableLoopManager() {
            private boolean isRunning;

            @Override
            public void start() {
                this.isRunning = true;
                new Thread(() -> {
                    while(true) {
                            var now = System.currentTimeMillis();
                            var residuo = System.currentTimeMillis() - now;
                            loop.loop(PERIOD + residuo);
                            residuo = System.currentTimeMillis() - now;
                            GameLoopFactoryImpl.this.delay(residuo);
                        }
                    }
                ).start();
            }

            @Override
            public void stop() {
                if (this.isRunning) {
                    this.isRunning = false;
                }
            }

            @Override
            public boolean isRunning() {
                return this.isRunning;
            }

            @Override
            public void addObserver(Observer observer) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'addObserver'");
            }

            @Override
            public void notifyObservers() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'notifyObservers'");
            }
            
        };
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

    private class LoopManagerTimer extends AnimationTimer implements ObservableLoopManager {

        private boolean isRunning;
        private long lastFrame = 0;
        private final Set<Observer> observers;

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
            try {
                loop.loop(dt);    
                GameLoopFactoryImpl.this.delay(dt);
            } catch (Exception e) {
                this.stop();
                System.err.println(ExceptionUtils.advacendDisplay(e));
                ViewLegacy.javaFxFactory().mainAppManager().get().safeError();
            }
        }

        @Override
        public boolean isRunning() {
            return this.isRunning;
        }

        @Override
        public void stop() {
            this.notifyObservers();
            if (this.isRunning) {
                super.stop();
                this.isRunning = false;
            }
        }

        @Override
        public void addObserver(final Observer observer) {
            this.observers.add(observer);
        }

        @Override
        public void notifyObservers() {
            this.observers.forEach(Observer::update);
        }
    }
}
