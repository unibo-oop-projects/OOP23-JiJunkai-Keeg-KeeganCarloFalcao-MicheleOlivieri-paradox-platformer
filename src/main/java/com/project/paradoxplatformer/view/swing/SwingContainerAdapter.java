package com.project.paradoxplatformer.view.swing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.project.paradoxplatformer.controller.input.KeyAssetterImpl;
import com.project.paradoxplatformer.controller.input.api.InputTranslator;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.controller.input.api.KeyAssetter;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;
import com.project.paradoxplatformer.view.renders.ViewComponent;

import static com.project.paradoxplatformer.utils.OptionalUtils.peek;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableDoubleValue;


public class SwingContainerAdapter implements GraphicContainer<JComponent, KeyEvent>, InputTranslator<KeyEvent>{

    private final JPanel pane;
    private final KeyAssetter<KeyEvent> keyAssetter;


    public SwingContainerAdapter(JPanel panel) {
        this.pane = panel;
        
        this.keyAssetter = new KeyAssetterImpl<>(this);
    }

    @Override
    public KeyAssetter<KeyEvent> getKeyAssetter() {
        return new KeyAssetterImpl<>(this.keyAssetter);
    }

    @Override
    public void activateKeyInput(Runnable activateInput) {
        activateInput.run();
        this.pane.addKeyListener(this.new SwingKeyListener());
    }

    @Override
    public Optional<InputType> translate(KeyEvent t) {
        return InputType.getString(KeyEvent.getKeyText(t.getKeyCode()).toUpperCase());
    }

    @Override
    public Dimension dimension() {
        return new Dimension(this.pane.getWidth(), this.pane.getHeight());
    }

    @Override
    public void setDimension(double width, double height) {
        this.pane.setSize((int)width, (int)height);
    }

    @Override
    public boolean render(ViewComponent<JComponent> component) {
        return Optional.ofNullable(component.unwrap())
            .map(peek(c -> this.pane.add(c)))
            .map(c -> true)
            .orElse(false);
    }

    @Override
    public boolean delete(ViewComponent<JComponent> component) {
        return Optional.ofNullable(component.unwrap())
            .map(peek(this.pane::remove))
            .map(c -> true)
            .orElse(false);
    }

    @Override
    public ObservableDoubleValue widthProperty() {
        return new SimpleDoubleProperty(this.pane.getWidth());
    }

    @Override
    public ObservableDoubleValue heightProperty() {
        return new SimpleDoubleProperty(this.pane.getWidth());
    }

    private class SwingKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            
        }
    
        @Override
        public void keyPressed(KeyEvent e) {
            SwingContainerAdapter.this.keyAssetter.add(e);
        }
    
        @Override
        public void keyReleased(KeyEvent e) {
            SwingContainerAdapter.this.keyAssetter.remove(e);
        }

    }

    
    
}
