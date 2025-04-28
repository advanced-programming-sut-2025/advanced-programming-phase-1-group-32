package models;

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
        int allRandomElementsNumber; //TODO: fix this
        int numOfElements = random.nextInt(max - min + 1) + min;
        for (int i = 0; i < numOfElements; i++) {
            //TODO: need an enum of Placables?
        }
    }

    private void initializeMap() {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = (new Tile(new Position(i, j)));
            }
        }
    }

    public Tile[][] getTiles() {
        return tiles.clone();
    }

    public Tile getTileByPosition(Position position) {
        return tiles[position.getRow()][position.getCol()];
    }

}