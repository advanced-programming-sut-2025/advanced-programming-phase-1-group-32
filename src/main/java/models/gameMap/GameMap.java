package models.gameMap;

import models.App;
import models.Position;
import models.Tile;
import models.entities.Entity;
import models.enums.TileType;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameMap implements Serializable {
    private Tile[][] tiles;
    private int width, height;
    private Environment environment;
    private final Set<Entity> entities = new HashSet<>();
    private final ArrayList<MapRegion> regions = new ArrayList<>();

    public Set<Entity> getEntities() {
        return entities;
    }

    private GameMap(){}
    private GameMap(TileType[][] tileTypes){

    }
    public GameMap(){

    }

    public GameMap(MapData data, Environment environment){
        TileType[][] typeMap = data.getTypeMap();
        MapRegion[][] regionMap = data.getRegionMap();
        this.environment = environment;

        if(data.regions != null){
            this.regions.addAll(data.regions);
        }

        this.height = typeMap.length;
        this.width = typeMap[0].length;
        this.tiles = new Tile[height][width];

        for(int i = 0 ; i < height ; i++){
            for(int j = 0 ; j < width ; j++){
                tiles[i][j] = new Tile(new Position(i, j), typeMap[i][j], regionMap[i][j]);
            }
        }
        initializeMap();
    }
    transient SecureRandom random = new SecureRandom();

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
        return getTileByPosition(position.getRow(),position.getCol());
    }

    public Tile getTileByPosition(int row, int col) {
        if(row > tiles.length || col > tiles[0].length)
            return null;
        return tiles[row][col];
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addEntity(Entity entity){
        this.entities.add(entity);
    }
    public void removeEntity(Entity entity){
        this.entities.remove(entity);
    }

    public ArrayList<MapRegion> getRegions() {
        return regions;
    }
}