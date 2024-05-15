package com.project.paradoxplatformer.utils.world;

public class Tile {
    private boolean solid;

    public Tile(boolean solid) {
        this.solid = solid;
    }

    public boolean isSolid() {
        return solid;
    }
}
