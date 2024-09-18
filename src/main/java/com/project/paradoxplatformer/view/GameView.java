package com.project.paradoxplatformer.view;

import com.project.paradoxplatformer.model.entity.ReadOnlyMutableObjectWrapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.view.graphics.GraphicAdapter;
import com.project.paradoxplatformer.view.graphics.ReadOnlyGraphicDecorator;

import java.util.List;

/**
 * Interface representing a view in the game.
 * <p>
 * This interface provides methods for initializing the view, managing and
 * updating
 * graphical controls, and handling dimensions of the view.
 * </p>
 *
 * @param <C> The type of context used by {@link GraphicAdapter} and
 *            {@link ReadOnlyGraphicDecorator}
 */
public interface GameView<C> {

    /**
     * Initializes the game view. This method should be called to set up
     * the view before any updates or interactions occur.
     */
    void init();

    /**
     * Retrieves an unmodifiable list of graphic controls in the view.
     * <p>
     * This method provides access to the current graphical controls without
     * allowing modifications to the list.
     * </p>
     *
     * @return a {@link List} of {@link GraphicAdapter} instances representing
     *         the current controls in the view
     */
    List<GraphicAdapter<C>> getUnmodifiableControls();

    /**
     * Gets the dimension of the game view.
     * <p>
     * This method returns the size of the view, which is used for layout and
     * rendering purposes.
     * </p>
     *
     * @return the {@link Dimension} of the view
     */
    Dimension dimension();

    /**
     * Updates the state of a graphical control based on the provided mutable object
     * and graphic decorator.
     * <p>
     * This method is used to synchronize the graphical representation with the
     * state of the underlying game object.
     * </p>
     *
     * @param readOnlyMutable the {@link ReadOnlyMutableObjectWrapper} containing
     *                        the state information for the control
     * @param readOnlyGraphic the {@link ReadOnlyGraphicDecorator} providing
     *                        the graphical representation to be updated
     */
    void updateControlState(ReadOnlyMutableObjectWrapper readOnlyMutable, ReadOnlyGraphicDecorator<C> readOnlyGraphic);

    /**
     * Removes a graphical node from the view.
     * <p>
     * This method is used to remove and clean up graphical controls that are no
     * longer needed or should be hidden.
     * </p>
     *
     * @param node the {@link ReadOnlyGraphicDecorator} to be removed from the view
     */
    void removeGraphic(ReadOnlyGraphicDecorator<C> node);
}
