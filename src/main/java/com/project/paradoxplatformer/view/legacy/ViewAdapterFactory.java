package com.project.paradoxplatformer.view.legacy;

import java.util.function.Function;
import java.util.function.Supplier;

import com.project.paradoxplatformer.view.graphics.GraphicContainer;
import com.project.paradoxplatformer.view.javafx.ViewMappingFactory;
import com.project.paradoxplatformer.view.manager.ViewManager;

/**
 * A factory interface for creating view adapters that manage different types of
 * UI components.
 * <p>
 * This interface provides methods for obtaining a view manager, creating blank
 * and loading pages,
 * obtaining a factory for view mapping components, and mapping containers to
 * graphic containers.
 * </p>
 *
 * @param <V> the type of view component (e.g., JavaFX {@code Node}, Swing
 *            {@code JComponent})
 * @param <P> the type of container used for the view components (e.g., JavaFX
 *            {@code Pane}, Swing {@code JPanel})
 * @param <K> the type of input event used for the view (e.g., JavaFX
 *            {@code KeyCode}, Swing {@code KeyEvent})
 */
public interface ViewAdapterFactory<V, P, K> {

    /**
     * Provides a supplier for obtaining the main application manager.
     * 
     * @return a {@link Supplier} that provides an instance of {@link ViewManager}.
     */
    Supplier<ViewManager> mainAppManager();

    /**
     * Creates a blank page of type {@code P}.
     * <p>
     * This method returns a blank page that serves as a placeholder or initial
     * state for the view.
     * </p>
     * 
     * @return a blank page of type {@code P}.
     */
    P blankPage();

    /**
     * Creates a loading page of type {@code P}.
     * <p>
     * This method returns a page that indicates a loading state, typically shown
     * while content is being loaded.
     * </p>
     * 
     * @return a loading page of type {@code P}.
     */
    P loadingPage();

    /**
     * Provides a supplier for obtaining the view mapping factory.
     * <p>
     * The view mapping factory is used to create mappings between view components
     * and their corresponding
     * representations.
     * </p>
     * 
     * @return a {@link Supplier} that provides an instance of
     *         {@link ViewMappingFactory}.
     */
    Supplier<ViewMappingFactory<V>> getComponentsFactory();

    /**
     * Provides a function that maps a container of type {@code P} to a
     * {@link GraphicContainer} of type {@code V}.
     * <p>
     * This method is used to adapt the container to a graphic representation of the
     * view components.
     * </p>
     * 
     * @return a {@link Function} that maps a container of type {@code P} to a
     *         {@link GraphicContainer} of type {@code V}.
     */
    Function<P, GraphicContainer<V, K>> containerMapper();
}
