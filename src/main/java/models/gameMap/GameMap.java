package models.gameMap;

import models.App;
import models.Position;
import models.building.Building;
import models.entities.Entity;
import models.entities.EntityList;
import models.entities.components.EntityComponent;
import models.entities.systems.EntityPlacementSystem;
import models.enums.TileType;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameMap {
    protected Tile[][] tiles;
    protected int width, height;
    protected Environment environment;
    protected final EntityList entities = new EntityList();
    protected Building building = null;

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
    public void addEntity(Entity entity){
        if(entities.contains(entity)) throw new RuntimeException("you fucked up somewhere and now the entity " + entity.getName() + " is getting " +
                "added twice int the map.");

        this.entities.add(entity);
    }
    public void removeEntity(Entity entity){
        if(!entities.contains(entity)) throw new RuntimeException("you fucked up somewhere and now the entity " + entity.getName() + " is getting " +
                "removed from the map, but it doesnt exist on the map");
        this.entities.remove(entity);
    }
    public ArrayList<Entity> getEntitiesWithComponent(Class<? extends EntityComponent> clazz){
        ArrayList<Entity> out = new ArrayList<>();
        for(Entity e : entities){
            if(e.getComponent(clazz) != null) out.add(e);
        }
        return out;
    }

    public GameMap(MapData data, Environment environment){
        TileType[][] typeMap = data.getTypeMap();
        this.environment = environment;
        this.height = typeMap.length;
        this.width = typeMap[0].length;
        this.tiles = new Tile[height][width];

        for(int i = 0 ; i < height ; i++){
            for(int j = 0 ; j < width ; j++){
                tiles[i][j] = new Tile(new Position(j, i), typeMap[i][j], this);
            }
        }
    }

    private void generateRandomElements(int min ,int max) { //inclusive
        //TODO
    }

    public Tile[][] getTiles() {
        return tiles.clone();
    }

    public Tile getTileByPosition(Position position) {
        return getTileByPosition(position.getRow(),position.getCol());
    }

    public Tile getTileByPosition(int row, int col) {
        if(row >= tiles.length || row < 0 || col >= tiles[0].length || col < 0)
            return null;
        return tiles[row][col];
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}