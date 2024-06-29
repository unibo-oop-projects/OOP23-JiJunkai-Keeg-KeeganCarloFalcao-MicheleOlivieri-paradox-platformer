package com.project.paradoxplatformer.model.inputmodel;

import java.util.Map;
import java.util.EnumMap;

import com.project.paradoxplatformer.utils.commands.Command;
import com.project.paradoxplatformer.utils.commands.actions.CommandActionFactory;
import com.project.paradoxplatformer.utils.commands.actions.CommandActionFactoryImpl;
import com.project.paradoxplatformer.view.fxcomponents.keyinputs.api.InputType;

public class InputFactoryImpl implements InputFactory {

    private final CommandActionFactory cmdFactory = new CommandActionFactoryImpl();
    @Override
    public InputModel standardModel() {
        return () -> new EnumMap<InputType, Command>(Map.of(
            InputType.LEFT, cmdFactory.leftCommand(),
            InputType.RIGHT, cmdFactory.rightCommand(),
            InputType.UP, cmdFactory.upCommand()
        ));
    }

    @Override
    public InputModel wasdModel() {
        return () -> new EnumMap<InputType, Command>(Map.of(
            InputType.A, cmdFactory.leftCommand(),
            InputType.D, cmdFactory.rightCommand(),
            InputType.W, cmdFactory.upCommand()
        ));
    }

    @Override
    public InputModel invertedModel() {
        return () -> new EnumMap<InputType, Command>(Map.of(
            InputType.LEFT, cmdFactory.rightCommand(),
            InputType.RIGHT, cmdFactory.leftCommand(),
            InputType.UP, cmdFactory.upCommand()
        ));
    }

    @Override
    public InputModel advancedModel() {
        return new StdModelDecorator(wasdModel());
    }
    
    private class StdModelDecorator implements InputModel{
        private InputModel toDecorate;
        private EnumMap<InputType, Command> inputmodel;

        private StdModelDecorator(InputModel toDecorate) {
            this.toDecorate = toDecorate;
            inputmodel = this.toDecorate.getModel();
            inputmodel.putAll(standardModel().getModel());
        }

        @Override
        public EnumMap<InputType, Command> getModel() {
            return inputmodel;
        }

    }

    
}
