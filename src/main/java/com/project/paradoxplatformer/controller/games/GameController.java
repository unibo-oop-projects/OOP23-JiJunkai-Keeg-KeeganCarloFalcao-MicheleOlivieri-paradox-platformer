package com.project.paradoxplatformer.controller.games;

import com.project.paradoxplatformer.utils.InvalidResourceException;

public interface GameController {

    void loadModel();

    void syncView() throws InvalidResourceException;

    void update(final long l);

}
