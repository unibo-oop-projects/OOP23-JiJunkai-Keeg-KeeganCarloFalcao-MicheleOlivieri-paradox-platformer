package com.project.paradoxplatformer.view.fxcomponents;

import javafx.scene.image.Image;
import java.util.List;

public interface Spriter {
    

    List<Image> getIdleImage();

    List<Image> runningImages();
}
