package com.project.paradoxplatformer.controller;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import com.project.paradoxplatformer.MainApplication;
import com.project.paradoxplatformer.PageIdentifier;
import com.project.paradoxplatformer.ViewManager;
import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.world.GameModelData;
import com.project.paradoxplatformer.view.Page;

public class ControllerImpl implements Controller {

    // private final GameController gController;
    // private final InputController<ControllableObject> inputController;
    private final ViewManager view;
    private static final CountDownLatch latch = new CountDownLatch(1);

    public ControllerImpl(ViewManager vm) {
        // this.gController = gController;
        // this.inputController = iC;
        view = vm;
    }

    @Override
    public void updateTimer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTimer'");
    }

    @Override
    public void updateGame(final long dt) {
        // this.inputController.inject(model.getWorld().player(), ControllableObject::stop);
        // this.gController.update(dt);
    }

    @Override
    public void quit() {
        //Proply use view to close
        System.exit(0);
    }

    @Override
    public void start() {
        new Thread(() -> view.create(latch)).start();
        
        try {
            latch.await();
            System.out.println("Application Started");
            view.performReactiveAction(() -> this.switchView(PageIdentifier.MENU, "level1.json"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println("\nErrors encounterd:\nILLEGAL STATE → " + e.getMessage() + "\n");
            view.safeError();
        }
        
    }

    private void switchView(PageIdentifier id, String param){
        if(view instanceof MainApplication ma) {
            try {
                ma.switchPage(id).create(param);
            }catch (Exception ex){
                view.displayError(Optional.ofNullable(ex.getCause())
                    .map(Throwable::getClass)
                    .map(Class::getSimpleName)
                    .orElse(ex.getClass().getSimpleName()) + " \nRaised → " +  
                    Optional.ofNullable(ex.getMessage())
                    .map(m -> 
                        Optional.ofNullable(ex.getCause())
                        .map(Throwable::getMessage)
                        .or(() -> 
                            Optional.ofNullable(ex.getCause())
                                .filter(RuntimeException.class::isInstance)
                                .map(RuntimeException.class::cast)
                                .map(RuntimeException::getMessage)
                        )
                        .map(m::concat)
                        .orElse(m)
                    ).orElse("")
                ); 
                System.exit(-1);
            }
        }     
    }
    
}
