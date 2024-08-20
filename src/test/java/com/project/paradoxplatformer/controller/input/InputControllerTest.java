package com.project.paradoxplatformer.controller.input;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.controller.input.api.KeyAssetter;
import com.project.paradoxplatformer.controller.input.api.KeyInputer;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.*;
import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class InputControllerTest {

   
    @Test
    void testMultiKey() {

        var factory = new InputMovesFactoryImpl();
        var inputController = new InputController<>(factory.wasdModel());

        PlayerModel player = new PlayerModel(new Coord2D(0, 0), new Dimension(30, 30));

        //Simulate a jump and moving simulteanosly
        KeyInputerImpl keyInput = new KeyInputerImpl();
        keyInput.simulateKeyAdd("D");
        keyInput.simulateKeyAdd("W");

        //first frame update
        inputController.checkPool(keyInput.getKeyAssetter(), player, ControllableObject::stop);
        player.updateState(15);

        //register the position for further assertions
        var prevPos = registerPosition(player);
        assertTrue(prevPos.x() > 0. && prevPos.y() > 0);

        //remove the keys from the pool to check the stop excecution
        keyInput.simulateKeyRemove("D");
        keyInput.simulateKeyRemove("W");

        //second frame updtate
        inputController.checkPool(keyInput.getKeyAssetter(), player, ControllableObject::stop);
        player.updateState(15);

        var stoppingPos = registerPosition(player);
        //jumping still continues while x pos is stopped
        assertTrue(prevPos.x() == stoppingPos.x());

    }

    private Coord2D registerPosition(PlayerModel player) {
        return player.getPosition();
    }

    private class KeyInputerImpl implements KeyInputer<String> {

        private final KeyAssetter<String> keyassetter;

        public KeyInputerImpl() {
            this.keyassetter = new KeyAssetterImpl<>(InputType::getString);
        }

        public void simulateKeyAdd(String key) {
            this.keyassetter.add(key);
        }

        public void simulateKeyRemove(String key) {
            this.keyassetter.remove(key);
        }

        @Override
        public KeyAssetter<String> getKeyAssetter() {
            return new KeyAssetterImpl<>(this.keyassetter);
        }

        @Override
        public void activateKeyInput(Runnable activateInput) {
        }

    }
}
