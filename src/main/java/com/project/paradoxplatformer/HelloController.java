package com.project.paradoxplatformer;

import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;
import java.util.Objects;
import java.util.ResourceBundle;

import com.project.paradoxplatformer.controller.deserialization.DeserializerFactory;
import com.project.paradoxplatformer.controller.deserialization.DeserializerFactoryImpl;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.controller.gameloop.ControllerImpl;
import com.project.paradoxplatformer.controller.gameloop.TaskLoopFactory;
import com.project.paradoxplatformer.controller.gameloop.GameLoopFactoryImpl;
import com.project.paradoxplatformer.controller.gameloop.LoopManager;
import com.project.paradoxplatformer.controller.gameloop.Controller;
import com.project.paradoxplatformer.controller.games.GameControllerImpl;
import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.InputFactory;
import com.project.paradoxplatformer.model.inputmodel.InputFactoryImpl;
import com.project.paradoxplatformer.model.inputmodel.InputModel;
import com.project.paradoxplatformer.model.inputmodel.commands.Command;
import com.project.paradoxplatformer.model.obstacles.api.Obstacle;
import com.project.paradoxplatformer.model.world.ModelData;
import com.project.paradoxplatformer.model.world.PlatfromModelData;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.ButtonComponent;
import com.project.paradoxplatformer.view.fxcomponents.containers.GraphicContainerImpl;
import com.project.paradoxplatformer.view.fxcomponents.containers.api.GraphicContainer;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.MotionBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.application.*;
import java.util.Map;
import java.util.List;

public class HelloController implements Initializable{

    @FXML
    private AnchorPane gamePane;

    @FXML
    private VBox pausePane;

    private ModelData f;
    private LoopManager loopManager;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        pausePane.setVisible(false);
        DeserializerFactory dFactory = new DeserializerFactoryImpl();
        LevelDTO level = null;
        try {
            level = dFactory.levelDeserialzer().deserialize("level1.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Objects.requireNonNull(level);
        f = new PlatfromModelData(level);
        
        final GraphicContainer g = new GraphicContainerImpl(gamePane);
        final GameController gc = new GameControllerImpl(f, g);
        gc.loadModel();
        gc.syncView();

        g.activateKeyInput(() -> Platform.runLater(gamePane::requestFocus));
        
        g.setKeyPressed();
        g.setKeyReleased();
        
        InputFactory imfactory = new InputFactoryImpl(); 
        InputController<ControllableObject> ic = new InputController<>(imfactory.advancedModel(), g.getKeyAssetter());
        GraphicContainer pause = new GraphicContainerImpl(pausePane);

        var res = new ButtonComponent(new Button(), new Dimension(0,0), new Coord2D(0, 0), "RESUME");
        var ret = new ButtonComponent(new Button(), new Dimension(0,0), new Coord2D(0, 0), "RETRY");
        res.onAction(() -> {
            gamePane.setEffect(null);
            this.loopManager.start();
            pausePane.setVisible(false);
            g.activateKeyInput(() -> Platform.runLater(gamePane::requestFocus));
        });
        ret.onAction(() -> {
            this.stop();
            this.gamePane.getChildren().clear();
            this.pausePane.getChildren().removeIf(Button.class::isInstance);
            gamePane.setEffect(null);
            this.initialize(null, null);
        });
        var qui = new ButtonComponent(new Button(), new Dimension(0,0), new Coord2D(0, 0), "QUIT");
        qui.onAction(() -> {
            Platform.exit();
        });
        List.of(res,ret,qui).forEach(pause::render);

        InputModel<LoopManager> w = () -> new EnumMap<>(Map.of(
            InputType.P, this::showPauseMenu,
            InputType.ESCAPE, this::showPauseMenu,
            InputType.R, l -> {
                gamePane.setEffect(null);
                l.start();
                pausePane.setVisible(false);
            }
        ));
        InputController<LoopManager> ig = new InputController<>(w, g.getKeyAssetter());
        
        final Controller cont = new ControllerImpl(gc, ic, f);
        TaskLoopFactory gFactory = new GameLoopFactoryImpl(dt -> {
            cont.updateGame(dt);
            //cont.updateTimer();
        });

        loopManager = gFactory.animationLoop();

        TaskLoopFactory kk = new GameLoopFactoryImpl(l -> ig.inject(this.loopManager, o -> {}));
        kk.animationLoop().start();


        Thread tr = new Thread(() -> {
            while(true) {
                var r = System.currentTimeMillis();
                // ic.inject(f);
                var min_frame_waiting = 1000 / 40;
                var residuo = System.currentTimeMillis() - r;
                gc.update(min_frame_waiting + residuo);
                residuo = System.currentTimeMillis() - r;
                try {
                    Thread.sleep(min_frame_waiting - residuo);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        // tr.start();
        
        loopManager.start();
    }

    private void showPauseMenu(LoopManager l) { 
        gamePane.setEffect(new GaussianBlur(10.));
        l.stop();
        pausePane.setVisible(true);
    }

    @FXML
    protected void test() {
        //GAME LOOP
        /*
         * update player position
         * update all obstacle position
         * check collision between player and world objects
         * i.e if player is on first ground, its acceleration on y is 0
         */
        
        f.getWorld().obstacles().forEach(Obstacle::effect);
        
    }

    @FXML
    protected void reset() {
        this.stop();
        this.gamePane.getChildren().clear();
        this.initialize(null, null);
    }

    @FXML
    protected void stop() {
        this.loopManager.stop();
    }

    @FXML
    protected void resume() {
        this.loopManager.start();
    }

    
}