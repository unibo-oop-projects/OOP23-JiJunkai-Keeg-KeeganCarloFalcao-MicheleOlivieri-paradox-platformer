package com.project.paradoxplatformer.view.fxcomponents.keyinputs;

import javafx.scene.input.KeyEvent;
import java.util.Set;

import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.InputModel;
import com.project.paradoxplatformer.view.fxcomponents.keyinputs.api.InputType;
import com.project.paradoxplatformer.view.fxcomponents.keyinputs.api.FXKeyAssetter;

import java.util.HashSet;

public class KeyAssetterImpl implements FXKeyAssetter{

    private Set<InputType> pool;

    public KeyAssetterImpl() {
        this.pool = new HashSet<InputType>();
    }

    //Made it this way for beaty purposes on KeyImputer graphic container
    //Now need to thick about correcteness and give up on code beauty

    //REMINDER: prlly add and remove need Inputype as paramater, hence making this class indipendent 
    //to fx library and potentially moving it to controller branch. Such move lets 
    //this cyclepool method regarding only model components, and it's good
     
    public boolean remove(KeyEvent e) {
        return this.pool.remove(new KeyTranslatorFX(e.getCode()).translate());
    }

    public boolean add(KeyEvent e) {
        return this.pool.add(new KeyTranslatorFX(e.getCode()).translate());
    }

    @Override
    public void cyclePool(InputModel modelInput, ControllableObject executor) {
        if(!pool.isEmpty()) {
            this.pool.stream()
                .filter(in -> !in.equals(InputType.UNDEFINED))
                .filter(modelInput.getModel()::containsKey)
                .map(modelInput.getModel()::get)
                .forEach(c -> c.execute(executor));
        }
        else {
            executor.stop();
        }
        
    }

}
