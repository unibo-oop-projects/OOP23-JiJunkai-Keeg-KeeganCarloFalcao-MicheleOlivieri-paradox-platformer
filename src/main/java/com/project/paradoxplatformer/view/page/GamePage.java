package com.project.paradoxplatformer.view.page;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.project.paradoxplatformer.controller.deserialization.DeserializerFactoryImpl;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.controller.games.GameControllerImpl;
import com.project.paradoxplatformer.controller.games.Level;
import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.model.GameModel;
import com.project.paradoxplatformer.model.GameModelImpl;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.innersetting.SimpleGameSettingsModel;
import com.project.paradoxplatformer.model.inputmodel.InputMovesFactoryImpl;
import com.project.paradoxplatformer.utils.ImageLoader;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.view.GameViewImpl;
import com.project.paradoxplatformer.view.GameView;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;
import com.project.paradoxplatformer.view.legacy.ViewFramework;
import com.project.paradoxplatformer.view.settings.GameSettings;
import com.project.paradoxplatformer.view.settings.SimpleGameSettings;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;

/**
 * Controller for the game's main page.
 * This class manages to initiate the model, view and controller for the
 * specified level of the game.
 * It runs on the JavaFX main thread son a console implementation should not be
 * allowed to create it.
 */
public final class GamePage extends AbstractThreadedPage {

        @FXML
        private AnchorPane gamePane;

        @FXML
        private StackPane pagePane;

        @FXML
        private HBox pauseBox;

        /**
         * {@inheritDoc}
         */
        @FXML
        @Override
        public void initialize(final URL location, final ResourceBundle resources) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void runOnFXThread(final Level param) throws IOException, InvalidResourceException {
                // this.pausePane.setVisible(true);
                // HERE's WHERE MAGIC HAPPENS, looks very free needs to be coupled atleast
                final LevelDTO level = this.getLevel(param);

                this.setgameCointainerBackground(level.getBackgroundFile());
                final var mappingFactory = ViewFramework.javaFxFactory()
                                .getComponentsFactory()
                                .get();

                final GameModel gameModel = new GameModelImpl(level);
                final GraphicContainer<Node, KeyCode> gameGraphContainer = ViewFramework.javaFxFactory()
                                .containerMapper()
                                .apply(this.gamePane);
                final GameView<Node> gameView = new GameViewImpl<>(level, gameGraphContainer, mappingFactory);

                final GameController<Node> gameController = new GameControllerImpl<>(gameModel, gameView, param);
                final InputController<ControllableObject> inputController = new InputController<>(
                                new InputMovesFactoryImpl().advancedModel());

                this.initModelAndView(gameController);
                gameGraphContainer.activateKeyInput(() -> Platform.runLater(gamePane::requestFocus));
                // System.out.println(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
                gameController.startGame(inputController, gameGraphContainer, level.getType());

                final GraphicContainer<Node, KeyCode> gameSettingsContainer = ViewFramework.javaFxFactory()
                                .containerMapper()
                                .apply(this.pauseBox);
                final GameSettings<Node> gameSettings = new SimpleGameSettings<>(
                                new SimpleGameSettingsModel(SimpleGameSettingsModel.basicSettings()),
                                gameController,
                                gameSettingsContainer,
                                mappingFactory);
                gameSettings.init();

        }

        private void setgameCointainerBackground(final String backgroundFile) throws InvalidResourceException {
                gamePane.setBackground(new Background(new BackgroundImage(
                                ImageLoader.createFXImage(backgroundFile),
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundRepeat.NO_REPEAT,
                                BackgroundPosition.CENTER,
                                new BackgroundSize(100, 100, true, true, false, true))));
        }

        private void initModelAndView(final GameController<Node> gc) throws InvalidResourceException {
                gc.loadModel();
                gc.syncView();
        }

        private LevelDTO getLevel(final Level level) throws IOException, InvalidResourceException {
                return new DeserializerFactoryImpl()
                                .levelDeserialzer()
                                .deserialize(level.getResourceFile());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
                return "Main Game Controller";
        }
}
