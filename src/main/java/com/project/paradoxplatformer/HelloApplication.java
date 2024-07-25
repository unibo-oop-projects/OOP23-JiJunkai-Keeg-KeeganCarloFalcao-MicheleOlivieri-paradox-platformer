package com.project.paradoxplatformer;

import com.project.paradoxplatformer.controller.SimpleController;
import com.project.paradoxplatformer.view.javafx.JavaFxApp;

public class HelloApplication{

    public static void main(String[] args) {
        new SimpleController(
                new JavaFxApp(), 
                "Paradox Platformer"
            )
            .start();
    }
}