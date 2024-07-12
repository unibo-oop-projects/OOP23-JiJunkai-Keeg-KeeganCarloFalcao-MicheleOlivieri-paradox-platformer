package com.project.paradoxplatformer;

import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import javax.crypto.spec.SecretKeySpec;

import com.project.paradoxplatformer.controller.deserialization.DeserializerFactory;
import com.project.paradoxplatformer.controller.deserialization.DeserializerFactoryImpl;
import com.project.paradoxplatformer.controller.deserialization.dtos.LevelDTO;
import com.project.paradoxplatformer.controller.gameloop.ControllerImpl;
import com.project.paradoxplatformer.controller.gameloop.TaskLoopFactory;
import com.project.paradoxplatformer.controller.gameloop.GameLoopFactoryImpl;
import com.project.paradoxplatformer.controller.gameloop.LoopManager;
import com.project.paradoxplatformer.controller.gameloop.ObservableLoopManager;
import com.project.paradoxplatformer.controller.gameloop.Controller;
import com.project.paradoxplatformer.controller.games.GameControllerImpl;
import com.project.paradoxplatformer.controller.games.GameController;
import com.project.paradoxplatformer.controller.input.InputController;
import com.project.paradoxplatformer.controller.input.api.InputType;
import com.project.paradoxplatformer.model.entity.dynamics.ControllableObject;
import com.project.paradoxplatformer.model.inputmodel.InputMovesFactory;
import com.project.paradoxplatformer.model.inputmodel.InputMovesFactoryImpl;
import com.project.paradoxplatformer.model.inputmodel.InputModel;
import com.project.paradoxplatformer.model.world.ModelData;
import com.project.paradoxplatformer.model.world.PlatfromModelData;
import com.project.paradoxplatformer.utils.SecureWrapper;
import com.project.paradoxplatformer.utils.geometries.Dimension;
import com.project.paradoxplatformer.utils.geometries.api.observer.Observer;
import com.project.paradoxplatformer.utils.geometries.coordinates.Coord2D;
import com.project.paradoxplatformer.view.fxcomponents.FXButtonAdapter;
import com.project.paradoxplatformer.view.fxcomponents.FXContainerAdapter;
import com.project.paradoxplatformer.view.game.GamePlatformView;
import com.project.paradoxplatformer.view.game.GameView;
import com.project.paradoxplatformer.view.graphics.GraphicContainer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.application.*;

import java.util.Map;
import java.util.List;

public class HelloController implements Initializable{

    private static final String KEY = "0123456789abcdef";

    @FXML
    private AnchorPane gamePane;

    @FXML
    private VBox pausePane;

    private ModelData f;
    private ObservableLoopManager loopManager;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        gamePane.widthProperty().addListener((obs, o, n) -> {
            var u = n.doubleValue() / 500;
            System.out.println("NEW " + n);
            System.out.println("OLD " + o);
            System.out.println("RATIO "+ u);
        });
        
        //HUST FOR TESTINGS

        pausePane.setVisible(false);
        DeserializerFactory dFactory = new DeserializerFactoryImpl();
        LevelDTO level = null;
        try {
            level = dFactory.levelDeserialzer().deserialize("level1.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(Objects.isNull(level)) {
            throw new IllegalArgumentException();
        }
        f = new PlatfromModelData(level);
        //BETTER HAVE INTERNAL METHODS TO ACCESS TO MODEL OR VIEW COMPONENTS
        //E:G COTTROLLER Acceses to gameview, if controlloer wants to manipulate gamview
        //he can do it only via Gameview
        final GraphicContainer<Node> g = new FXContainerAdapter(gamePane);
        final GameView gameV = new GamePlatformView(level, g);
        final GameController gc = new GameControllerImpl(f, gameV);
        gc.loadModel();
        gc.syncView();

        g.activateKeyInput(() -> Platform.runLater(gamePane::requestFocus));
        
        g.setKeyPressed();
        g.setKeyReleased();
        
        InputMovesFactory imfactory = new InputMovesFactoryImpl(); 
        InputController<ControllableObject> ic = new InputController<>(imfactory.advancedModel(), g.getKeyAssetter().get());
        GraphicContainer<Node> pause = new FXContainerAdapter(pausePane);
        
        
        var res = new FXButtonAdapter(new Dimension(0,0), new Coord2D(0, 0), "RESUME");
        var ret = new FXButtonAdapter(new Dimension(0,0), new Coord2D(0, 0), "RETRY");
        res.onAction(() -> {
            gamePane.setEffect(null);
            this.loopManager.start();
            pausePane.setVisible(false);
            g.activateKeyInput(() -> Platform.runLater(gamePane::requestFocus));
        });
        ret.onAction(() -> {
            loopManager.stop();
            this.gamePane.getChildren().clear();
            //MUST FIX, if retry then pause must not rinitialize
            this.pausePane.getChildren().removeIf(Button.class::isInstance);
            gamePane.setEffect(null);
            this.initialize(null, null);
        });
        var qui = new FXButtonAdapter(new Dimension(0,0), new Coord2D(0, 0), "QUIT");
        qui.onAction(() -> {
            Platform.exit();
        });
        List.of(res,ret,qui).forEach(pause::render);

        InputModel<LoopManager> w = () -> new EnumMap<>(Map.of(
            InputType.P, LoopManager::stop,
            InputType.ESCAPE, LoopManager::stop,
            InputType.T, l -> this.test()
        ));

        // gamePane.setEffect(null);
        //         // l.start();
        //         // pausePane.setVisible(false);
        InputController<LoopManager> ig = new InputController<>(w, g.getKeyAssetter().get());
        
        final Controller cont = new ControllerImpl(gc, ic, f);
        TaskLoopFactory gFactory = new GameLoopFactoryImpl(dt -> {
            cont.updateGame(dt);
            //cont.updateTimer();
        });

        loopManager = gFactory.animationLoop();

        TaskLoopFactory kk = new GameLoopFactoryImpl(dt -> ig.inject(this.loopManager, o -> {}));
        kk.animationLoop().start();

        Observer lo = new Observer() {
            
            @Override
            public void update() {
                if(!loopManager.isRunning() && Objects.isNull(gamePane.getEffect()) && !pausePane.isVisible()) {
                    gamePane.setEffect(new GaussianBlur(10.));
                    pausePane.setVisible(true);
                }
            }

        };
        loopManager.addObserver(lo);

        // SecureWrapper<String> j = SecureWrapper.of("Damn");
        // j.secure(new Random(1234));
        
        // j.release(new Random(12345));
        // System.out.println(j.get());
        
        // Thread tr = new Thread(() -> {
        //     while(true) {
        //         var r = System.currentTimeMillis();
        //         // ic.inject(f);
        //         var min_frame_waiting = 1000 / 40;
        //         var residuo = System.currentTimeMillis() - r;
        //         gc.update(min_frame_waiting + residuo);
        //         residuo = System.currentTimeMillis() - r;
        //         try {
        //             Thread.sleep(min_frame_waiting - residuo);
        //         } catch (InterruptedException e) {
        //             // TODO Auto-generated catch block
        //             e.printStackTrace();
        //         }
        //     }
        // });
        // tr.start();
        
        loopManager.start();
    }

    private void showPauseMenu(LoopManager l) { 
        gamePane.setEffect(new GaussianBlur(10.));
        l.stop();
        System.out.println("RUNN");
        pausePane.setVisible(true);
    }

    @FXML
    protected void test() {
        
        f.getWorld().obstacles().forEach(ob -> ob.effect(Optional.of(f.getWorld().player())));
        // A im = new A(new Dimension(10, 10), new Coord2D(0, 0), "player.png");
        // im.deliver();
        
    }
}