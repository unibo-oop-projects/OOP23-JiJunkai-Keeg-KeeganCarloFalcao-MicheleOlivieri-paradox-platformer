package com.project.paradoxplatformer.view.fxcomponents.keyinputs;

import javafx.scene.input.KeyEvent;

public interface KeyAssetter {

    public boolean remove(KeyEvent e);

    public boolean add(KeyEvent e);

    void cyclePool();

}
