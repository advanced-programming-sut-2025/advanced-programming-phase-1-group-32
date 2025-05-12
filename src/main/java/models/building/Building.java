package models.building;

import models.App;
import models.entities.components.EntityComponent;
import models.entities.components.PositionComponent;
import models.entities.systems.EntityPlacementSystem;
import models.gameMap.*;
import models.entities.Entity;
import models.entities.components.Placeable;
import models.enums.TileType;
import models.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Building extends Entity {
    private GameMap interior;
    private TileType[][] exterior;
    private final String interiorMapName;
    private final String exteriorMapName;
    private final Position position;
    private final Environment environment;

    public Building(BuildingData data, Position position) {
        super(data.name, new Placeable(false));
        for (EntityComponent c : data.components) {
            this.addComponent(c);
        }

        this.interiorMapName = data.interiorMap;
        this.environment = data.environment;
        this.position = position;
        this.exteriorMapName = data.exterior;

        MapData exteriorData = App.mapRegistry.getData(this.exteriorMapName);
        MapData interiorData = App.mapRegistry.getData(this.interiorMapName);
        this.interior = new GameMap(interiorData, environment);
        this.exterior = exteriorData.getTypeMap();

        GameMap worldMap = App.getActiveGame().getMainMap();

        for(int i = 0 ; i < exterior.length ; i++){
            for(int j = 0 ; j < exterior[0].length; j++){
                Tile worldTile = worldMap.getTileByPosition(i + position.getRow(), j + position.getCol());
                TileType exteriorTile = exterior[i][j];

                if(exteriorTile != null){
                    worldTile.setType(exteriorTile);
                }
            }
        }


        if(exteriorData.getDoors() != null){
            Map<Integer, Door> exteriorDoors = new HashMap<>();
            Map<Integer, Door> interiorDoors = new HashMap<>();
            Map<Integer, Integer> inOutRefs = new HashMap<>();
            Map<Integer, Integer> outInRefs = new HashMap<>();
            for (MapData.MapLayerData<String>.ObjectData d : exteriorData.getDoors()) {
                int id = d.getProperty("id").asInt;
                int dest = d.getProperty("destination").asInt;
                Door door = new Door();
                exteriorDoors.putIfAbsent(id, door);
                EntityPlacementSystem.placeOnTile(door, worldMap.getTileByPosition(position.getRow() + d.y - 1, position.getCol() + d.x));
                outInRefs.putIfAbsent(id, dest);
            }
            for (MapData.MapLayerData<String>.ObjectData d : interiorData.getDoors()) {
                int id = d.getProperty("id").asInt;
                int dest = d.getProperty("destination").asInt;
                Door door = new Door();
                interiorDoors.putIfAbsent(id, door);
                EntityPlacementSystem.placeOnTile(door, interior.getTileByPosition(d.y-1, d.x));
                inOutRefs.putIfAbsent(id, dest);
            }

            for(Map.Entry<Integer, Integer> p : outInRefs.entrySet()){
                exteriorDoors.get(p.getKey()).setDestination(interiorDoors.get(p.getValue()).getComponent(PositionComponent.class).get());
            }
            for(Map.Entry<Integer, Integer> p : inOutRefs.entrySet()){
                interiorDoors.get(p.getKey()).setDestination(exteriorDoors.get(p.getValue()).getComponent(PositionComponent.class).get());
            }


        }

        this.interior.setBuilding(this);

        worldMap.addEntity(this);
    }
}
