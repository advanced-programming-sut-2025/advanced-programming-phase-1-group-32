package models;

import models.enums.TileType;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameMap {
    private Tile[][] tiles;

    public GameMap(int m, int n) {
        this.tiles = new Tile[m][n];
        initializeMap();
    }

    SecureRandom random = new SecureRandom();

    private void generateRandomElements(int min ,int max) { //inclusive
        //TODO
    }

    private void initializeMap() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new Tile(new Position(i, j), Math.random() > 0.6 ? TileType.GRASS : TileType.HOED_GROUND);
                if(Math.random() > 0.6){
                    tiles[i][j].setContent(App.entityRegistry.makeEntity("Birch Tree"));
                }
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles.clone();
    }
<<<<<<< HEAD
=======

    public Tile getTileByPosition(Position position) {
        return tiles[position.getRow()][position.getCol()];
    }

>>>>>>> Ali
}