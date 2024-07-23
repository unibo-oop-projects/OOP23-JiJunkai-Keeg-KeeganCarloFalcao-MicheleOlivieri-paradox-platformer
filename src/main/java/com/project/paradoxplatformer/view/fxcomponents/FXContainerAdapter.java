package com.project.paradoxplatformer.view.fxcomponents;

import com.project.paradoxplatformer.controller.input.KeyAssetterImpl;
import com.project.paradoxplatformer.controller.input.api.InputTranslator;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.controller.input.api.KeyAssetter;
import com.project.paradoxplatformer.utils.SecureWrapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;
import com.project.paradoxplatformer.view.renders.ViewComponent;

import java.lang.Runnable;

import javafx.beans.value.ObservableDoubleValue;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.function.Consumer;

public class FXContainerAdapter implements GraphicContainer<Node>, InputTranslator<KeyCode>{

    private final SecureWrapper<Pane> uiContainer;
    private final KeyAssetter keyAssetter;
    private boolean isActive;

    public FXContainerAdapter(Pane container) {
        this.uiContainer = SecureWrapper.of(container);
        this.keyAssetter = new KeyAssetterImpl();
    }

    @Override
    public boolean render(final ViewComponent<Node> component){
        
        return uiContainer.get().getChildren().add(component.unwrap());
    }

    @Override
    public Dimension dimension() {
        return new Dimension(this.uiContainer.get().getPrefWidth(), this.uiContainer.get().getPrefHeight());
    }

    @Override
    public void setDimension(double width, double height) {
        this.uiContainer.get().setPrefSize(width, height);
    }

    @Override
    public void setKeyPressed() {
        if(this.isActive) {
            this.uiContainer.get().addEventFilter(
                KeyEvent.KEY_PRESSED, 
                e -> this.decoupleAction(e.getCode(), this.keyAssetter::add)
            );
        }
    }

    @Override
    public void setKeyTyped() {
        if(this.isActive) {
            this.uiContainer.get().addEventFilter(
                KeyEvent.KEY_TYPED, 
                e -> this.decoupleAction(e.getCode(), this.keyAssetter::add)
            );
        }
    }

    @Override
    public void setKeyReleased() {
        if(this.isActive) {
            this.uiContainer.get().addEventFilter(
                KeyEvent.KEY_RELEASED, 
                e -> this.decoupleAction(e.getCode(), this.keyAssetter::remove)
            );
        }
    }

    @Override
    public SecureWrapper<KeyAssetter> getKeyAssetter() {
        return SecureWrapper.of(this.keyAssetter);
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

    @Override
    public ObservableDoubleValue widthProperty() {
        return this.uiContainer.get().widthProperty();
    }

    @Override
    public ObservableDoubleValue heightProperty() {
        return this.uiContainer.get().heightProperty();
    }

    @Override
    public boolean delete(ViewComponent<Node> component) {
        return this.uiContainer.get().getChildren().remove(component.unwrap());
    }
    
}
