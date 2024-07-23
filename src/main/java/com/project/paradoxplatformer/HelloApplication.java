package com.project.paradoxplatformer;
import com.project.paradoxplatformer.controller.ControllerImpl;

public class HelloApplication{

    public static void main(String[] args) {
        new ControllerImpl(new MainApplication()).start();
        // State<String> l = SimpleState.create("damn")
        //     .onSubscribe(System.out::println);

        // l.subscribe();
        // l = l.updateState("exs");
        // l.subscribe();
        
    }
}