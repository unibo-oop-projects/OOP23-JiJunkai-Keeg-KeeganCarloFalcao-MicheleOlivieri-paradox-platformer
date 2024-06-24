package com.project.paradoxplatformer.view.fxcomponents.inputs;

import com.project.paradoxplatformer.utils.inputs.InputTranslator;

import javafx.scene.input.KeyEvent;
import java.util.Set;
import java.util.HashSet;

public class KeyAssetterImpl implements KeyAssetter{

    private Set<InputType> pool;

    public KeyAssetterImpl() {
        this.pool = new HashSet<InputType>();
    }

    public boolean remove(KeyEvent e) {
        return this.pool.remove(new KeyTranslatorFX(e.getCode()).translate());
    }

    public boolean add(KeyEvent e) {
        return this.pool.add(new KeyTranslatorFX(e.getCode()).translate());
    }

}
