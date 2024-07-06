package com.project.paradoxplatformer.view.graphics.sprites;

import java.util.List;

public interface Spriter<I> {

    List<I> getIdleImage();

    List<I> runningImages();

    List<I> jumpingImages();

    List<I> fallingImages();
}
