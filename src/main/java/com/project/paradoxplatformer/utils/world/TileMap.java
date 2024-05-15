package com.project.paradoxplatformer.utils.world;

import java.util.ArrayList;
import java.util.List;

public class TileMap {
    private List<List<Tile>> tiles;

    public TileMap(int width, int height) {
        tiles = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            List<Tile> column = new ArrayList<>();
            for (int y = 0; y < height; y++) {
                column.add(null); // Initialize with null tiles
            }
            tiles.add(column);
        }
    }

    public void setTile(int x, int y, Tile tile) {
        if (isValidPosition(x, y)) {
            tiles.get(x).set(y, tile);
        }
    }

    public Tile getTile(int x, int y) {
        if (isValidPosition(x, y)) {
            return tiles.get(x).get(y);
        } else {
            return null;
        }
    }

    public int getWidth() {
        return tiles.size();
    }

    public int getHeight() {
        return tiles.isEmpty() ? 0 : tiles.get(0).size();
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < tiles.size() && y >= 0 && y < tiles.get(0).size();
    }
}
