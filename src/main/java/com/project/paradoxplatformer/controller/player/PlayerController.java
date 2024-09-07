package com.project.paradoxplatformer.controller.player;

import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.view.player.PlayerView;

public final class PlayerController {

    private PlayerModel playerModel;
    private PlayerView playerView;

    public PlayerController(PlayerModel model, PlayerView view) {
        this.playerModel = model;
        this.playerView = view;
    }

    public void updateState(long dt) {
        this.playerModel.updateState(dt);
        this.playerView.updateState(playerModel.getPosition());
    }
}
