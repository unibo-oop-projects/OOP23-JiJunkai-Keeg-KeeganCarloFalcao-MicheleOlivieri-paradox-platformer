package com.project.paradoxplatformer.view.javafx.fxcomponents;

import com.project.paradoxplatformer.controller.input.KeyAssetterImpl;
import com.project.paradoxplatformer.controller.input.api.InputTranslator;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.controller.input.api.KeyAssetter;
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

/**
 * An adapter for a JavaFX {@link Pane} that implements the
 * {@link GraphicContainer} and {@link InputTranslator} interfaces.
 * This class handles rendering of graphical components, managing dimensions,
 * and translating key inputs.
 */
public class FXContainerAdapter implements GraphicContainer<Node, KeyCode>, InputTranslator<KeyCode> {

    private final Pane uiContainer;
    private final KeyAssetter<KeyCode> keyAssetter;

    /**
     * Constructs an FXContainerAdapter with the specified JavaFX {@link Pane}.
     * 
     * @param container the JavaFX pane to be used as the container for graphical
     *                  components
     */
    public FXContainerAdapter(Pane container) {
        this.uiContainer = container;
        this.keyAssetter = new KeyAssetterImpl<>(this);
    }

    @Override
    public boolean render(final ViewComponent<Node> component) {
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
    public KeyAssetter<KeyCode> getKeyAssetter() {
        return new KeyAssetterImpl<>(this.keyAssetter);
    }

    @Override
    public void activateKeyInput(Runnable activateInput) {
        activateInput.run();
        this.manageKeyEvent(KeyEvent.KEY_PRESSED, this.keyAssetter::add);
        this.manageKeyEvent(KeyEvent.KEY_RELEASED, this.keyAssetter::remove);
    }

    @Override
    public Optional<InputType> translate(KeyCode k) {
        return InputType.getString(k.name());
    }

    /**
     * Applies the given action to the specified key code.
     * 
     * @param e      the key code
     * @param action the action to apply
     */
    private void decoupleAction(KeyCode e, Consumer<KeyCode> action) {
        action.accept(e);
    }

    @Override
    public ObservableDoubleValue widthProperty() {
        return this.uiContainer.widthProperty();
    }

    @Override
    public ObservableDoubleValue heightProperty() {
        return this.uiContainer.heightProperty();
    }

    @Override
    public boolean delete(ViewComponent<Node> component) {
        return this.uiContainer.getChildren().remove(component.unwrap());
    }

    /**
     * Registers an event filter for the specified key event type and applies the
     * given action to the key code.
     * 
     * @param eventType the type of key event (e.g., key pressed or key released)
     * @param action    the action to apply to the key code
     */
    private void manageKeyEvent(EventType<KeyEvent> eventType, Consumer<KeyCode> action) {
        this.uiContainer.addEventFilter(
                eventType,
                e -> this.decoupleAction(e.getCode(), action));
    }
}
