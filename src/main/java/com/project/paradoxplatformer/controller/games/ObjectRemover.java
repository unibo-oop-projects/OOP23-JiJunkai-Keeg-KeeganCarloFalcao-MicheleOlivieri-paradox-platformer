package com.project.paradoxplatformer.controller.games;

import com.project.paradoxplatformer.model.GameModelData;
import com.project.paradoxplatformer.model.entity.CollidableGameObject;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.view.game.GameView;
import com.project.paradoxplatformer.view.graphics.ReadOnlyGraphicDecorator;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Map;

public class ObjectRemover<C> {
    private final GameModelData gameModel;
    private final GameView<C> gameView;
    private final List<MutableObject> objects;

    public ObjectRemover(GameModelData gameModel, GameView<C> gameView) {
        this.gameModel = gameModel;
        this.gameView = gameView;
        this.objects = new ArrayList<>();
    }

    public void handleRemoveObject(final PageIdentifier id, Optional<? extends CollidableGameObject> self) {
        self.filter(MutableObject.class::isInstance)
                .map(MutableObject.class::cast)
                .ifPresentOrElse(objects::add,
                        () -> System.out.println("Cannot remove object. It is not a MutableGameObject."));
    }

    public void removeGameObjects(Map<MutableObject, ReadOnlyGraphicDecorator<C>> gamePairs) {
        gamePairs.entrySet().removeIf(entry -> {
            MutableObject key = entry.getKey();
            if (objects.contains(key)) {
                gameModel.actionOnWorld(w -> w.removeGameObjects(key));
                gameView.removeGraphic(entry.getValue());
                return true;
            }
            return false;
        });
    }
}
