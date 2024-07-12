package com.project.paradoxplatformer.controller.gameloop;

public interface TaskLoopFactory {
    
    ObservableLoopManager animationLoop();

    LoopManager threadLoop();
}
