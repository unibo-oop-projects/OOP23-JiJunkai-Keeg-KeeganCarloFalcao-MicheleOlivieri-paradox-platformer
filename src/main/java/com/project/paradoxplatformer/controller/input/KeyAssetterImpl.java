package com.project.paradoxplatformer.controller.input;

import java.util.Set;

import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.controller.input.api.KeyAssetter;
import com.project.paradoxplatformer.model.inputmodel.InputModel;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;

import java.util.HashSet;

public class KeyAssetterImpl implements KeyAssetter{

    private final Set<InputType> pool;

    public KeyAssetterImpl() {
        this.pool = new HashSet<InputType>();
    }

    //Made it this way for beaty purposes on KeyImputer graphic container
    //Now need to thick about correcteness and give up on code beauty

    //REMINDER: prlly add and remove need Inputype as paramater, hence making this class indipendent 
    //to fx library and potentially moving it to controller branch. Such move lets 
    //this cyclepool method regarding only model components, and it's good

    //CHECK DONE IT; IT THING IS ON CONTROLLER NOW DAMNN
     
    public boolean remove(final InputType key) {
        return this.pool.remove(key);
    }

    public boolean add(final InputType key) {
        return this.pool.add(key);
    }

    @Override
    public <T> void cyclePool(final InputModel<T> modelInput, T executor, Command<T> onIdle) {
        
        if(!pool.isEmpty()) {
            this.pool.stream()
                .filter(in -> !in.equals(InputType.UNDEFINED))
                .filter(modelInput.getModel()::containsKey)
                .map(modelInput.getModel()::get)
                .forEach(c -> c.execute(executor));
        }
        else {
            onIdle.execute(executor);
        }
    }

}
