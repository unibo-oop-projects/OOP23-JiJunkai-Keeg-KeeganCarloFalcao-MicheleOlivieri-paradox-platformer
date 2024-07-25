 package com.project.paradoxplatformer.view.javafx.fxcomponents;

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
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.Optional;
import java.util.function.Consumer;

public class FXContainerAdapter implements GraphicContainer<Node, KeyCode>, InputTranslator<KeyCode>{

    private final SecureWrapper<Pane> uiContainer;
    private final KeyAssetter<KeyCode> keyAssetter;
    private boolean isActive;

    public FXContainerAdapter(Pane container) {
        this.uiContainer = SecureWrapper.of(container);
        this.keyAssetter = new KeyAssetterImpl<>(this);
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
        this.manageKeyEvent(KeyEvent.KEY_PRESSED, this.keyAssetter::add);
    }

    @Override
    public void setKeyReleased() {
        this.manageKeyEvent(KeyEvent.KEY_RELEASED, this.keyAssetter::remove);
    }

    @Override
    public KeyAssetter<KeyCode> getKeyAssetter() {
        return new KeyAssetterImpl<>(this.keyAssetter);
    }

    @Override
    public void activateKeyInput(Runnable activateInput) {
        activateInput.run();
        this.isActive = true;
    }

    @Override
    public Optional<InputType> translate(KeyCode k) {
        return InputType.getString(k.name());
    }

    private void decoupleAction(KeyCode e, Consumer<KeyCode> action) {
        action.accept(e);
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

    private void manageKeyEvent(EventType<KeyEvent> eventType, Consumer<KeyCode> action) {
        if(this.isActive) {
            this.uiContainer.get().addEventFilter(
                eventType, 
                e -> this.decoupleAction(e.getCode(), action)
            );
        }
    }
    
}
