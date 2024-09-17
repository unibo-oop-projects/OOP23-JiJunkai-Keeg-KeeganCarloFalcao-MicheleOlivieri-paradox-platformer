package com.project.paradoxplatformer.controller.games;

import com.project.paradoxplatformer.model.GameModelData;
import com.project.paradoxplatformer.model.entity.MutableObject;
import com.project.paradoxplatformer.utils.collision.api.CollidableGameObject;
import com.project.paradoxplatformer.view.game.GameView;
import com.project.paradoxplatformer.view.graphics.ReadOnlyGraphicDecorator;
import com.project.paradoxplatformer.view.javafx.PageIdentifier;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Map;

/**
 * ObjectRemover manages the removal of dynamic objects from the game world and
 * view.
 * It keeps track of mutable objects that need to be removed and handles their
 * removal
 * during the game loop.
 *
 * @param <C> The type of graphics associated with the game view.
 */
public final class ObjectRemover<C> {
    private final GameModelData gameModel; // Model data for interacting with the game world
    private final GameView<C> gameView; // View component for rendering game graphics
    private final List<MutableObject> objects; // List of mutable objects to be removed

    /**
     * Constructs an ObjectRemover with the given game model and game view.
     *
     * @param gameModel The model containing game data and world state.
     * @param gameView  The view responsible for rendering graphics.
     */
    public ObjectRemover(final GameModelData gameModel, final GameView<C> gameView) {
        this.gameModel = gameModel;
        this.gameView = Optional.of(gameView).get();
        this.objects = new ArrayList<>();
    }

    /**
     * Adds a mutable object to the removal list if it is of type MutableObject.
     *
     * @param id   The identifier of the page or view where the object is located.
     * @param self An optional object that may be a MutableObject to be removed.
     */
    public void handleRemoveObject(final PageIdentifier id, final Optional<? extends CollidableGameObject> self) {
        self.filter(MutableObject.class::isInstance)
                .map(MutableObject.class::cast)
                .ifPresentOrElse(this.objects::add,
                        () -> System.out.println("Cannot remove object. It is not a MutableGameObject."));
    }

    /**
     * Removes the specified game objects from the game world and view.
     * This method removes objects that are in the removal list and updates the game
     * world and view.
     *
     * @param gamePairs A map of game objects to their associated graphics
     *                  decorators.
     */
    public void removeGameObjects(final Map<MutableObject, ReadOnlyGraphicDecorator<C>> gamePairs) {
        gamePairs.entrySet().removeIf(entry -> {
            MutableObject key = entry.getKey();
            if (objects.contains(key)) {
                // Remove the game object from the game world
                gameModel.actionOnWorld(w -> w.removeGameObjects(key));
                // Remove the associated graphic from the view
                removeGraphic(entry.getValue());
                return true; // Indicate that the object was removed
            }
            return false; // Indicate that the object was not removed
        });
    }

    /**
     * Removes a graphic from the view.
     *
     * @param graphicDecorator The graphic decorator to be removed.
     */
    private void removeGraphic(final ReadOnlyGraphicDecorator<C> graphicDecorator) {
        gameView.removeGraphic(graphicDecorator);
    }
}
