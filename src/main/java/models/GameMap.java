package models;

import models.enums.TileType;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameMap {
    private Tile[][] tiles;
    int width, height;

    public GameMap(int height, int width) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[height][width];
        initializeMap();
    }

    SecureRandom random = new SecureRandom();

    private void generateRandomElements(int min ,int max) { //inclusive
        //TODO
    }

    private void initializeMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = new Tile(new Position(i, j), Math.random() > 0.6 ? TileType.GRASS : TileType.HOED_GROUND);
                if(Math.random() > 0.6){
                    tiles[i][j].setContent(App.entityRegistry.makeEntity("Pine Tree"));
                }
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles.clone();
    }

    public Tile getTileByPosition(Position position) {
        return tiles[position.getRow()][position.getCol()];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}