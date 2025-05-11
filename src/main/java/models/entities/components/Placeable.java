package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.App;
import models.Position;
import models.building.Door;
import models.entities.CollisionEvent;
import models.entities.Entity;
import models.entities.systems.EntityPlacementSystem;
import models.enums.TileType;
import models.gameMap.GameMap;
import models.gameMap.Tile;
import records.Result;

import java.util.ArrayList;
import java.util.Arrays;

public class Placeable extends EntityComponent{
    private TileType[][] exterior;

    private String exteriorMapName;

    @JsonProperty("isWalkable")
    private final boolean isWalkable;
    @JsonProperty("collisionFunctions")
    private ArrayList<CollisionEvent> collisionFunctions = new ArrayList<>();


    public Placeable(TileType[][] exterior, String exteriorMapName) {
        this.isWalkable = false;
        this.exterior = exterior;
        this.exteriorMapName = exteriorMapName;
    }

    public Placeable(boolean isWalkable, CollisionEvent... collisionFunctions) {
        this.isWalkable = isWalkable;
        this.collisionFunctions.addAll(Arrays.asList(collisionFunctions));
    }
    private Placeable(Placeable other){
        this.isWalkable = other.isWalkable;
        this.collisionFunctions.addAll(other.collisionFunctions);
    }
    public Placeable(){
        this(false);
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public Result place(PositionComponent position, GameMap interior) {
        GameMap worldMap = App.getActiveGame().getMainMap();

        for(int i = 0 ; i < exterior.length ; i++){
            for(int j = 0 ; j < exterior[0].length; j++){
                Tile worldTile = worldMap.getTileByPosition(j + position.getCol(), i + position.getRow());
                TileType exteriorTile = exterior[i][j];

                if(exteriorTile != null){
                    worldTile.setType(exteriorTile);
                }
                if(exterior[i][j] == TileType.DOOR){
                    EntityPlacementSystem.placeOnTile(new Door(interior), worldTile);
                }
            }
        }
        return null; //TODO
    }
    public Result place(PositionComponent position) {
        GameMap activeMap = App.getActiveGame().getActiveMap();

        for(int i = 0 ; i < exterior.length ; i++){
            for(int j = 0 ; j < exterior[0].length; j++){
                Tile activeTile = activeMap.getTileByPosition(j + position.getCol(), i + position.getRow());
                TileType exteriorTile = exterior[i][j];

                if(exteriorTile != null){
                    activeTile.setType(exteriorTile);
                }
            }
        }
        return null; //TODO
    }



    public ArrayList<CollisionEvent> getCollisionEvents() {
        return collisionFunctions;
    }

    @Override
    public String toString() {
        return "Placeable{" +
                "isWalkable=" + isWalkable +
                '}';
    }

    @Override
    public EntityComponent clone() {
        return new Placeable(this);
    }
}
