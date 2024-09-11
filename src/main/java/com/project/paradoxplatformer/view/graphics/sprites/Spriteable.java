package com.project.paradoxplatformer.view.graphics.sprites;

public interface Spriteable<S extends Enum<S>> {
    
    void animate(S status);

    boolean isSpecial();
}
