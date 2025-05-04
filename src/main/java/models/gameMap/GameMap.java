package models.gameMap;

import models.App;
import models.Position;
import models.Tile;
import models.enums.TileType;

import java.security.SecureRandom;

public class GameMap {
    private Tile[][] tiles;
    private int width, height;
    private Environment environment;

    private GameMap(TileType[][] tileTypes){

    }

    public GameMap(GameMapType type, Environment environment){
        MapData data = type.data;
        TileType[][] typeMap = data.getTypeMap();
        this.environment = environment;


        this.height = typeMap.length;
        this.width = typeMap[0].length;
        this.tiles = new Tile[height][width];

        for(int i = 0 ; i < height ; i++){
            for(int j = 0 ; j < width ; j++){
                tiles[i][j] = new Tile(new Position(i, j), typeMap[i][j]);
            }
        }
        initializeMap();
    }
    SecureRandom random = new SecureRandom();

    private void generateRandomElements(int min ,int max) { //inclusive
        //TODO
    }

    private void initializeMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(Math.random() > 0.8){
                    if(Math.random() > 0.6){
                        tiles[i][j].setContent(App.entityRegistry.makeEntity("Pine Tree"));
                    }else{
                        tiles[i][j].setContent(App.entityRegistry.makeEntity("Rock"));
                    }
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