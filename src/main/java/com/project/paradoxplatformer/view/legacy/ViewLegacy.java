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

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class ViewLegacy {

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

    public static ViewAdapterFactory<JComponent, JPanel, KeyEvent> swingFactory() {
        throw new UnsupportedOperationException("Unimplemented method 'swing'");
    }

    public static ViewAdapterFactory<String, String, String> console() {
        throw new UnsupportedOperationException("Unimplemented method 'console'");
    }

}
