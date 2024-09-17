package com.project.paradoxplatformer.controller.games;

import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

import java.util.Optional;

public interface GameControllerEventListener {

    void handleStopView(PageIdentifier id, Level param);

    void handleRemoveObject(PageIdentifier id, Optional<? extends CollidableGameObject> object);

    void handleTriggerEffect(PageIdentifier id, Obstacle param);

    void handleVictory(PageIdentifier id);

}