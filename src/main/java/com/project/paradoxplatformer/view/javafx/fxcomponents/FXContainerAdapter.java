package com.project.paradoxplatformer.view.javafx.fxcomponents;

import com.project.paradoxplatformer.controller.input.KeyAssetterImpl;
import com.project.paradoxplatformer.controller.input.api.InputTranslator;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.controller.input.api.KeyAssetter;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;
import com.project.paradoxplatformer.view.renders.ViewComponent;

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
    public FXContainerAdapter(final Pane container) {
        this.uiContainer = Optional.ofNullable(container)
            .orElseThrow(() -> new IllegalArgumentException("Cannot add an unknow pane as a container"));
        this.keyAssetter = new KeyAssetterImpl<>(this);
    }

    /**
     * Renders the specified component by adding its Node representation to the
     * container.
     * 
     * @param component the {@link ViewComponent} to render
     * @return true if the component was successfully added, false otherwise
     */
    @Override
    public boolean render(final ViewComponent<Node> component) {
        return uiContainer.getChildren().add(component.unwrap());
    }

    /**
     * Returns the current dimension of the container.
     * 
     * @return the dimension of the container
     */
    @Override
    public Dimension dimension() {
        return new Dimension(this.uiContainer.getPrefWidth(), this.uiContainer.getPrefHeight());
    }

    /**
     * Sets the preferred size of the container.
     * 
     * @param width  the new width of the container
     * @param height the new height of the container
     */
    @Override
    public void setDimension(final double width, final double height) {
        this.uiContainer.setPrefSize(width, height);
    }

    /**
     * Returns the {@link KeyAssetter} associated with this container.
     * 
     * @return the {@link KeyAssetter} for this container
     */
    @Override
    public KeyAssetter<KeyCode> getKeyAssetter() {
        return new KeyAssetterImpl<>(this.keyAssetter);
    }

    /**
     * Activates key input handling and sets up event filters for key press and
     * key release events.
     * 
     * @param activateInput a {@link Runnable} that enables key input handling
     */
    @Override
    public void activateKeyInput(final Runnable activateInput) {
        activateInput.run();
        this.manageKeyEvent(KeyEvent.KEY_PRESSED, this.keyAssetter::add);
        this.manageKeyEvent(KeyEvent.KEY_RELEASED, this.keyAssetter::remove);
    }

    /**
     * Translates the specified key code to an {@link InputType}.
     * 
     * @param k the key code to translate
     * @return an {@link Optional} containing the corresponding {@link InputType},
     *         or an empty {@link Optional} if no translation is available
     */
    @Override
    public Optional<InputType> translate(final KeyCode k) {
        return InputType.getString(k.name());
    }

    /**
     * Applies the given action to the specified key code.
     * 
     * @param e      the key code
     * @param action the action to apply
     */
    private void decoupleAction(final KeyCode e, final Consumer<KeyCode> action) {
        action.accept(e);
    }

    /**
     * Returns the width property of the container.
     * 
     * @return the width property
     */
    @Override
    public ObservableDoubleValue widthProperty() {
        return this.uiContainer.widthProperty();
    }

    /**
     * Returns the height property of the container.
     * 
     * @return the height property
     */
    @Override
    public ObservableDoubleValue heightProperty() {
        return this.uiContainer.heightProperty();
    }

    /**
     * Deletes the specified component from the container.
     * 
     * @param component the {@link ViewComponent} to delete
     * @return true if the component was successfully removed, false otherwise
     */
    @Override
    public boolean delete(final ViewComponent<Node> component) {
        return this.uiContainer.getChildren().remove(component.unwrap());
    }

    /**
     * Registers an event filter for the specified key event type and applies the
     * given action to the key code.
     * 
     * @param eventType the type of key event (e.g., key pressed or key released)
     * @param action    the action to apply to the key code
     */
    private void manageKeyEvent(final EventType<KeyEvent> eventType, final Consumer<KeyCode> action) {
        this.uiContainer.addEventFilter(
                eventType,
                e -> this.decoupleAction(e.getCode(), action));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicContainer<Node, KeyCode> defensiveCopy() {
        return new FXContainerAdapter(this.uiContainer);
    }
}
