package com.project.paradoxplatformer.controller.player;

import com.project.paradoxplatformer.model.player.Player;
import com.project.paradoxplatformer.view.player.PlayerView;

public class PlayerController {

    private Player playerModel;
    private PlayerView playerView;

    public PlayerController(Player model, PlayerView view) {
        this.playerModel = model;
        this.playerView = view;
    }

    public void updateState(long dt) {
        this.playerModel.update(dt);
        // this.playerView.updateState(playerModel.getPosition());
    }
}
