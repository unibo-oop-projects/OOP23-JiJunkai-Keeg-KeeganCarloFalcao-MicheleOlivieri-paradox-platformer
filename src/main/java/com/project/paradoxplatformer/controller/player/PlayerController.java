package com.project.paradoxplatformer.controller.player;

import com.project.paradoxplatformer.model.player.PlayerModel;
import com.project.paradoxplatformer.utils.world.Point;
import com.project.paradoxplatformer.utils.world.Vector;
import com.project.paradoxplatformer.view.player.PlayerView;

public class PlayerController {

    private PlayerModel playerModel;
    private PlayerView playerView;

    public PlayerController(Point position, Vector speed) {
        this.playerModel = new PlayerModel(position, speed);
        this.playerView = new PlayerView(position);
    }

    public void updateState(long dt) {
        this.playerModel.updateState(dt);
        this.playerView.updateState(playerModel.getPosition());
    }
}
