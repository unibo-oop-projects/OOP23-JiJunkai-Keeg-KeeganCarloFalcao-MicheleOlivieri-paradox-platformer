package com.project.paradoxplatformer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.project.paradoxplatformer.controller.deserialization.DeserializerFactoryImpl;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.controller.games.GameControllerImpl;
import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.model.GameModelData;
import com.project.paradoxplatformer.model.SimpleGameSettingsModel;
import com.project.paradoxplatformer.model.effect.api.Level;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.InputMovesFactoryImpl;
import com.project.paradoxplatformer.model.world.PlatfromModelData;
import com.project.paradoxplatformer.utils.ImageLoader;
import com.project.paradoxplatformer.utils.InvalidResourceException;
import com.project.paradoxplatformer.view.game.GamePlatformView;
import com.project.paradoxplatformer.view.game.GameView;
import com.project.paradoxplatformer.view.game.settings.GameSettings;
import com.project.paradoxplatformer.view.game.settings.SimpleGameSettings;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;
import com.project.paradoxplatformer.view.legacy.ViewLegacy;

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
import javafx.application.*;

public class GamePage<V, K> extends AbstractThreadedPage {

    @FXML
    private AnchorPane gamePane;

    @FXML
    private StackPane pagePane;

    @FXML
    private HBox pauseBox;

    public GamePage() {
        // CREATION COULD BE DONE HERE BUT SINCE FXML NEEDS AN NON-ARGUMENT CONTROCUTOR
        // EVERYTHING IS CREATED IN A SECURE STRICT create(Params) method, (such to
        // ensure a page structure).
        // FXML COULD TO THE START A CONTROLLER DYNAMICALLY (SET a FACTORY, via
        // CallBack) but
        // NEEDS A Complicated INJECTION HANDLING
    }

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pagePane.widthProperty().addListener((ob, old, n) -> System.out.println("Width: " + n));
        pagePane.heightProperty().addListener((ob, old, n) -> System.out.println("Height: " + n));
    }

    protected void test() {
        // gameModel.getWorld().obstacles().forEach(ob ->
        // ob.effect(Optional.of(gameModel.getWorld().player())));
    }

    // SHOULD PASS A PARAMETER DETANING THE MODEL STATE

    @Override
    protected void runOnFXThread(final Level param) throws Exception {
        // this.pausePane.setVisible(true);
        // HERE's WHERE MAGIC HAPPENS, looks very free needs to be coupled atleast
        final LevelDTO level = this.getLevel(param);

        var mappingFactory = ViewLegacy.javaFxFactory()
                .getComponentsFactory()
                .get();

        gamePane.setBackground(new Background(new BackgroundImage(
                ImageLoader.createFXImage(level.getBackgroundFile()),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true))));
        final GameModelData gameModel = new PlatfromModelData(level);
        final GraphicContainer<Node, KeyCode> gameGraphContainer = ViewLegacy.javaFxFactory().containerMapper()
                .apply(this.gamePane);
        final GameView<Node> gameView = new GamePlatformView<>(level, gameGraphContainer, mappingFactory);

        final GameController<Node> gameController = new GameControllerImpl<>(gameModel, gameView, param);
        final InputController<ControllableObject> inputController = new InputController<>(
                new InputMovesFactoryImpl().advancedModel());
        // FOR BETTER CLARITY THIS COULD BE DONE SEPARATELY

        this.initModelAndView(gameController);
        gameGraphContainer.activateKeyInput(() -> Platform.runLater(gamePane::requestFocus));
        // System.out.println(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        gameController.startGame(inputController, gameGraphContainer, level.getType());

        final GraphicContainer<Node, KeyCode> gameSettingsContainer = ViewLegacy.javaFxFactory().containerMapper()
                .apply(this.pauseBox);
        GameSettings<Node> gameSettings = new SimpleGameSettings<>(
                new SimpleGameSettingsModel(SimpleGameSettingsModel.basicSettings()),
                gameController,
                gameSettingsContainer,
                mappingFactory);
        gameSettings.init();

    }

    private void initModelAndView(final GameController<Node> gc) throws InvalidResourceException {
        gc.loadModel();
        gc.syncView();
    }

    private LevelDTO getLevel(Level level) throws IOException, InvalidResourceException {
        return new DeserializerFactoryImpl()
                .levelDeserialzer()
                .deserialize(level.getResourceFile());
    }

    @Override
    public String toString() {
        return "Main Game Controller";
    }

    // final GraphicContainer<Node> pause = new FXContainerAdapter(pausePane);
    // var res = new FXButtonAdapter(new Dimension(0,0), new Coord2D(0, 0),
    // "RESUME");
    // var ret = new FXButtonAdapter(new Dimension(0,0), new Coord2D(0, 0),
    // "RETRY");
    // res.onAction(() -> {
    // gamePane.setEffect(null);
    // this.loopManager.start();
    // pausePane.setVisible(false);
    // gameGraphContainer.activateKeyInput(() ->
    // Platform.runLater(gamePane::requestFocus));
    // });
    // ret.onAction(() -> {
    // loopManager.stop();
    // this.gamePane.getChildren().clear();
    // //MUST FIX, if retry then pause must not rinitialize
    // this.pausePane.getChildren().removeIf(Button.class::isInstance);
    // gamePane.setEffect(null);
    // try {
    // create(param);
    // pausePane.setVisible(false);
    // } catch (Exception e) {

    // }
    // });
    // var qui = new FXButtonAdapter(new Dimension(0,0), new Coord2D(0, 0), "QUIT");
    // qui.onAction(() -> {
    // Platform.exit();
    // });
    // List.of(res,ret,qui).forEach(pause::render);

    // InputModel<LoopManager> w = () -> new EnumMap<>(Map.of(
    // InputType.P, LoopManager::stop,
    // InputType.ESCAPE, LoopManager::stop,
    // InputType.T, l -> this.test()
    // ));

    // InputController<LoopManager> ig = new InputController<>(w);

    // TaskLoopFactory kk = new GameLoopFactoryImpl(dt ->
    // ig.cyclePool(gameGraphContainer.getKeyAssetter(), this.loopManager, o ->
    // {}));
    // kk.animationLoop().start();

    // Observer lo = new Observer() {

    // @Override
    // public void update() {
    // if(!loopManager.isRunning() && Objects.isNull(gamePane.getEffect()) &&
    // !pausePane.isVisible()) {
    // pausePane.setVisible(true);
    // gamePane.setEffect(new GaussianBlur(10.));

    // }
    // }
    // };

    // loopManager.addObserver(lo);
    // loopManager.start();
}