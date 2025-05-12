package models.entities.systems;

import models.App;
import models.Position;
import models.Vec2;
import models.building.Building;
import models.building.BuildingData;
import models.building.Door;
import models.entities.Entity;
import models.entities.components.InteriorComponent;
import models.entities.components.Placeable;
import models.entities.components.PositionComponent;
import models.enums.TileType;
import models.gameMap.GameMap;
import models.gameMap.MapData;
import models.gameMap.Tile;
import records.Result;

import javax.management.ImmutableDescriptor;
import java.util.HashMap;
import java.util.Map;

public class EntityPlacementSystem {
    public static Result placeOnTile(Entity entity, Tile tile){
        Entity tileEntity = tile.getContent();
        if(tileEntity != null){
            return new Result(false, "tile is full");
        }

        tile.setContent(entity);
        entity.addObserver(tile);
        tile.getMap().addEntity(entity);

        if(entity.getComponent(PositionComponent.class) == null){
            entity.addComponent(new PositionComponent(0, 0));
        }
        entity.getComponent(PositionComponent.class).setPosition(tile.getPosition()).setMap(tile.getMap());
        return new Result(true, "placed");
    }
    public static Result emptyTile(Tile tile){
        Entity tileEntity = tile.getContent();
        if(tileEntity != null){
            tileEntity.removeObserveer(tile);
            tile.getMap().removeEntity(tileEntity);
            tileEntity.removeComponent(PositionComponent.class);
        }
        tile.setContent(null);
        return new Result(true, "");
    }
    public static Result placeOnMap(Entity entity, Position position, GameMap map){
        map.addEntity(entity);

        if(entity.getComponent(PositionComponent.class) == null){
            entity.addComponent(new PositionComponent(0, 0));
        }
        if(entity.getComponent(PositionComponent.class).getMap() != null){
            entity.getComponent(PositionComponent.class).getMap().removeEntity(entity);
        }
        entity.getComponent(PositionComponent.class).setPosition(position).setMap(map);

        return new Result(true, "");
    }

    public static Result placeEntity(Entity entity, Vec2 position) {
        Placeable placeable = entity.getComponent(Placeable.class);
        if(placeable == null)
            return new Result(false, "This entity isn't placeable");
        InteriorComponent interior = entity.getComponent(InteriorComponent.class);
        if(interior != null){
            buildBuilding(entity, position);
        }
        return placeable.place(entity.getComponent(PositionComponent.class));
    }

    private static Result buildBuilding(Entity building, Vec2 position){
        InteriorComponent interiorComponent = building.getComponent(InteriorComponent.class);
        Placeable placeable = building.getComponent(Placeable.class);

        MapData exteriorData = App.mapRegistry.getData(placeable.getExteriorName());
        MapData interiorData = App.mapRegistry.getData(interiorComponent.getInteriorName());

        interiorComponent

        GameMap worldMap = App.getActiveGame().getMainMap();

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

    }
}
