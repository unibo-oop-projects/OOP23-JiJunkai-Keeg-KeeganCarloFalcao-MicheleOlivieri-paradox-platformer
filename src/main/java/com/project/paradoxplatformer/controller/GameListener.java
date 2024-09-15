package com.project.paradoxplatformer.controller;

import com.project.paradoxplatformer.model.effect.api.Level;
import com.project.paradoxplatformer.model.obstacles.Obstacle;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;

import java.util.Optional;

public interface GameListener {

    void handleStopView(PageIdentifier id, Level param);

    void handleRemoveObject(PageIdentifier id, Optional<? extends CollidableGameObject> object);

    void handleTriggerEffect(PageIdentifier id, Obstacle param);
}