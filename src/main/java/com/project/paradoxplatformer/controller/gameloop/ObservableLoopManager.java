package com.project.paradoxplatformer.controller.gameloop;

import com.project.paradoxplatformer.utils.geometries.api.observer.Observable;

/**
 * Embeds a {@code LoopManager} functionalities while suiting the
 * {@code Observable} pattern.
 */
public interface ObservableLoopManager extends LoopManager, Observable {

}
