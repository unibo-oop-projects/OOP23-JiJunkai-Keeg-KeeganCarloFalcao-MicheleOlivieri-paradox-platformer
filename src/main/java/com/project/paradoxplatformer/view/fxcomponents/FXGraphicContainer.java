package com.project.paradoxplatformer.view.fxcomponents;

import com.project.paradoxplatformer.controller.input.KeyAssetterImpl;
import com.project.paradoxplatformer.controller.input.api.InputTranslator;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.controller.input.api.KeyAssetter;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;
import com.project.paradoxplatformer.view.renders.Component;

import java.lang.Runnable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.function.Consumer;

public class FXGraphicContainer implements GraphicContainer<Node>, InputTranslator<KeyCode>{

    private final Pane uiContainer;
    private final KeyAssetter keyAssetter;
    private boolean isActive;

    public FXGraphicContainer(Pane container) {
        this.uiContainer = container;
        this.keyAssetter = new KeyAssetterImpl();
    }

    @Override
    public boolean render(Component<Node> component){
        return uiContainer.getChildren().add(component.unwrap());
    }

    @Override
    public Dimension dimension() {
        return new Dimension(this.uiContainer.getPrefWidth(), this.uiContainer.getPrefHeight());
    }

    @Override
    public void setDimension(double width, double height) {
        this.uiContainer.setPrefSize(width, height);
    }

    @Override
    public void setKeyPressed() {
        if(this.isActive) {
            this.uiContainer.addEventFilter(
                KeyEvent.KEY_PRESSED, 
                e -> this.decoupleAction(e.getCode(), this.keyAssetter::add)
            );
        }
    }

    @Override
    public void setKeyReleased() {
        if(this.isActive) {
            this.uiContainer.addEventFilter(
                KeyEvent.KEY_RELEASED, 
                e -> this.decoupleAction(e.getCode(), this.keyAssetter::remove)
            );
        }
    }

    @Override
    public KeyAssetter getKeyAssetter() {
        return this.keyAssetter;
    }

    @Override
    public void activateKeyInput(Runnable activateInput) {
        activateInput.run();
        this.isActive = true;
    }

    //MUST BE PRIVATE CAUSE ITS DONE INTERNALLY
    //TO FIX
    @Override
    public InputType translate(KeyCode k) {
        return InputType.getString(k.name());
    }

    private void decoupleAction(KeyCode e, Consumer<InputType> action) {
        action.accept(this.translate(e));
    }
    
}
