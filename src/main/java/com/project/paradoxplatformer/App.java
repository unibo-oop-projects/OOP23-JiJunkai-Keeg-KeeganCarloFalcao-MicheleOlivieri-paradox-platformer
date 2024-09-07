package com.project.paradoxplatformer;

import com.project.paradoxplatformer.controller.SimpleController;
import com.project.paradoxplatformer.view.legacy.ViewLegacy;

public class App{

    public static void main(String[] args) {
        new SimpleController<>(ViewLegacy.javaFxFactory(), "Paradox Platformer").start();   
    }
}