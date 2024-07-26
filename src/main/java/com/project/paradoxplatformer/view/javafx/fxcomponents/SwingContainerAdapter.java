package com.project.paradoxplatformer.view.javafx.fxcomponents;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.project.paradoxplatformer.controller.input.KeyAssetterImpl;
import com.project.paradoxplatformer.controller.input.api.InputTranslator;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.controller.input.api.KeyAssetter;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;
import com.project.paradoxplatformer.view.renders.ViewComponent;

import javafx.beans.value.ObservableDoubleValue;

public class SwingContainerAdapter implements GraphicContainer<JComponent, KeyEvent>, InputTranslator<KeyEvent>, KeyListener{

    private final JPanel pane;
    private final KeyAssetter<KeyEvent> keyAssetter;
    private boolean isActive;


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
        this.isActive = true;
        this.pane.addKeyListener(this);
    }

    @Override
    public Optional<InputType> translate(KeyEvent t) {
        return InputType.getString(KeyEvent.getKeyText(t.getKeyCode()).split("VK_")[1]);
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
        try {
            this.pane.add(component.unwrap());
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean delete(ViewComponent<JComponent> component) {
        try {
            this.pane.remove(component.unwrap());
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    public ObservableDoubleValue widthProperty() {
        throw new UnsupportedOperationException("Unimplemented method 'widthProperty'");
    }

    @Override
    public ObservableDoubleValue heightProperty() {
        throw new UnsupportedOperationException("Unimplemented method 'heightProperty'");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(this.isActive) {
            this.keyAssetter.add(e);
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(this.isActive) {
            this.keyAssetter.remove(e);
        }
        
    }
    
}
