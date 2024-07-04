package com.project.paradoxplatformer.model.inputmodel;

import java.util.Map;
import java.util.EnumMap;
import java.util.HashMap;

import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;
import com.project.paradoxplatformer.model.inputmodel.commands.actions.CommandActionFactory;
import com.project.paradoxplatformer.model.inputmodel.commands.actions.CommandActionFactoryImpl;



public class InputMovesFactoryImpl implements InputMovesFactory {

    private final CommandActionFactory cmdFactory;
    private Map<Command<ControllableObject>, Command<ControllableObject>> oppositeMap;


    public InputMovesFactoryImpl() {
        this.cmdFactory = new CommandActionFactoryImpl();
        this.oppositeMap = new HashMap<>(Map.of(
            cmdFactory.leftCommand(), cmdFactory.rightCommand(),
            cmdFactory.rightCommand(), cmdFactory.leftCommand()
        ));
    }

    @Override
    public InputModel<ControllableObject> standardModel() {
        return () -> new EnumMap<InputType, Command<ControllableObject>>(Map.of(
            InputType.LEFT, cmdFactory.leftCommand(),
            InputType.RIGHT, cmdFactory.rightCommand(),
            InputType.UP, cmdFactory.upCommand()
        ));
    }

    @Override
    public InputModel<ControllableObject> wasdModel() {
        return () -> new EnumMap<InputType, Command<ControllableObject>>(Map.of(
            InputType.A, cmdFactory.leftCommand(),
            InputType.D, cmdFactory.rightCommand(),
            InputType.W, cmdFactory.upCommand()
        ));
    }

    @Override
    public InputModel<ControllableObject> invertedModel(final InputModel<ControllableObject> ip) {
        return () -> {
            var inverted = new EnumMap<>(ip.getModel());
            inverted.entrySet().stream()
                .filter(e -> this.oppositeMap.containsKey(e.getValue()))
                .forEach(this::invert);
            return inverted;
        };
    }

    @Override
    public InputModel<ControllableObject> advancedModel() {
        return new StdModelDecorator(wasdModel());
    }
    
    private class StdModelDecorator implements InputModel<ControllableObject>{
        private final InputModel<ControllableObject> toDecorate;
        private final EnumMap<InputType, Command<ControllableObject>> inputmodel;

        private StdModelDecorator(final InputModel<ControllableObject> toDecorate) {
            this.toDecorate = toDecorate;
            this.inputmodel = this.toDecorate.getModel();
            this.inputmodel.putAll(InputMovesFactoryImpl.this.standardModel().getModel());
        }

        @Override
        public EnumMap<InputType, Command<ControllableObject>> getModel() {
            return this.inputmodel;
        }

    }

    private void invert(Map.Entry<InputType, Command<ControllableObject>> e) {
        e.setValue(this.oppositeMap.get(e.getValue()));
    }

    
}
