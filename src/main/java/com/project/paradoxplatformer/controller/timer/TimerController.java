package com.project.paradoxplatformer.controller.timer;

import com.project.paradoxplatformer.model.timer.TimerModel;

public class TimerController {
    private static TimerController instance;
    private TimerModel model;

    private TimerController() {
        model = new TimerModel();
    }

    public static TimerController getInstance() {
        if (instance == null) {
            instance = new TimerController();
        }
        return instance;
    }

    public TimerModel getModel() {
        return model;
    }

    public void startTimer() {
        model.startTimer();
    }

    public void stopTimer() {
        model.stopTimer();
    }

    public long getTimeElapsed() {
        return model.getTimeElapsed();
    }
}
