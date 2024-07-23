package com.project.paradoxplatformer.controller;


public interface Controller {

    void start();

    void updateTimer();

    void updateGame(final long dt);

    void quit();

}
