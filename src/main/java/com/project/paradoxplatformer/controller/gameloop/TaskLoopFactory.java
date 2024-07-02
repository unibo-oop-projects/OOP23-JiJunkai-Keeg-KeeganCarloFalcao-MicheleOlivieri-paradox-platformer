package com.project.paradoxplatformer.controller.gameloop;

public interface TaskLoopFactory {
    
    LoopManager animationLoop();

    LoopManager threadLoop();
}
