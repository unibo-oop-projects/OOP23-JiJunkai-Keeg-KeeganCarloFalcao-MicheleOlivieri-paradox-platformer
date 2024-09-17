package com.project.paradoxplatformer.view.legacy;

import java.awt.event.KeyEvent;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.project.paradoxplatformer.view.game.ViewMappingFactory;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;
import com.project.paradoxplatformer.view.javafx.JavaFxApp;
import com.project.paradoxplatformer.view.javafx.fxcomponents.FXContainerAdapter;
import com.project.paradoxplatformer.view.javafx.fxcomponents.FXViewMappingFactoryImpl;
import com.project.paradoxplatformer.view.manager.ViewManager;
import com.project.paradoxplatformer.view.swing.SwingApp;
import com.project.paradoxplatformer.view.swing.SwingContainerAdapter;
import com.project.paradoxplatformer.view.swing.SwingMapperFactoryImpl;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Provides factories for creating view adapters for different UI frameworks.
 * <p>
 * This class includes methods to obtain view adapter factories for JavaFX and
 * Swing,
 * as well as a placeholder for console-based view adapters.
 * </p>
 */
public final class ViewLegacy {

    // Private constructor to prevent instantiation
    private ViewLegacy() {
        throw new UnsupportedOperationException("Utility class should not be instantiated.");
    }

    /**
     * Creates a factory for adapting JavaFX views.
     * <p>
     * This factory provides methods to create blank pages, loading pages,
     * component factories, and container mappers specific to JavaFX.
     * </p>
     * 
     * @return a {@link ViewAdapterFactory} for JavaFX with {@link Node} and
     *         {@link Pane}
     *         as view components and {@link KeyCode} as input events.
     */
    public static ViewAdapterFactory<Node, Pane, KeyCode> javaFxFactory() {
        return new ViewAdapterFactory<>() {

            @Override
            public Pane blankPage() {
                return new StackPane(new Label("BLANK PAGE"));
            }

            @Override
            public Supplier<ViewMappingFactory<Node>> getComponentsFactory() {
                return FXViewMappingFactoryImpl::new;
            }

            @Override
            public Function<Pane, GraphicContainer<Node, KeyCode>> containerMapper() {
                return FXContainerAdapter::new;
            }

            @Override
            public Supplier<ViewManager> mainAppManager() {
                return JavaFxApp::getInstance;
            }

            @Override
            public Pane loadingPage() {
                return new StackPane(new Label("LOADING..."));
            }

        };
    }

    /**
     * Creates a factory for adapting Swing views.
     * <p>
     * This factory provides methods to create blank pages, loading pages,
     * component factories, and container mappers specific to Swing.
     * </p>
     * 
     * @return a {@link ViewAdapterFactory} for Swing with {@link JComponent} and
     *         {@link JPanel}
     *         as view components and {@link KeyEvent} as input events.
     */
    public static ViewAdapterFactory<JComponent, JPanel, KeyEvent> swingFactory() {
        return new ViewAdapterFactory<JComponent, JPanel, KeyEvent>() {

            @Override
            public JPanel blankPage() {
                return new JPanel();
            }

            @Override
            public Supplier<ViewMappingFactory<JComponent>> getComponentsFactory() {
                return SwingMapperFactoryImpl::new;
            }

            @Override
            public Function<JPanel, GraphicContainer<JComponent, KeyEvent>> containerMapper() {
                return SwingContainerAdapter::new;
            }

            @Override
            public Supplier<ViewManager> mainAppManager() {
                return SwingApp::new;
            }

            @Override
            public JPanel loadingPage() {
                return this.blankPage();
            }

        };
    }

    /**
     * Placeholder for a factory that adapts console-based views.
     * <p>
     * This method is not yet implemented and currently throws an exception
     * indicating that the console view adapter is not available.
     * </p>
     * 
     * @return a {@link ViewAdapterFactory} for console-based views.
     * @throws UnsupportedOperationException if the method is called
     *                                       before implementation.
     */
    public static ViewAdapterFactory<String, String, String> console() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'console'");
    }

}
