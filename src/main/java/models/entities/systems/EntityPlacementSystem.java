package models.entities.systems;

import models.App;
import models.Position;
import models.Vec2;
import models.building.Door;
import models.entities.Entity;
import models.entities.components.InteriorComponent;
import models.entities.components.Placeable;
import models.entities.components.PositionComponent;
import models.enums.TileType;
import models.gameMap.*;
import records.Result;

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
        Result result = placeExterior(entity, position);
        if(!result.isSuccessful())
            return result;

        if(interior != null){
            buildBuilding(entity, position);
        }


        return new Result(true, "placed");
    }

    private static Result buildBuilding(Entity building, Vec2 position){
        InteriorComponent interiorComponent = building.getComponent(InteriorComponent.class);
        Placeable placeable = building.getComponent(Placeable.class);

        MapData exteriorData = App.mapRegistry.getData(placeable.getExteriorName());
        MapData interiorData = App.mapRegistry.getData(interiorComponent.getInteriorName());

        //TODO environment?
        interiorComponent.setInteriorMap(new GameMap(interiorData, Environment.BUILDING));

        GameMap interiorMap = interiorComponent.getMap();
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
                EntityPlacementSystem.placeOnTile(door, interiorMap.getTileByPosition(d.y-1, d.x));
                inOutRefs.putIfAbsent(id, dest);
            }

            for(Map.Entry<Integer, Integer> p : outInRefs.entrySet()){
                exteriorDoors.get(p.getKey()).setDestination(interiorDoors.get(p.getValue()).getComponent(PositionComponent.class).get());
            }
            for(Map.Entry<Integer, Integer> p : inOutRefs.entrySet()){
                interiorDoors.get(p.getKey()).setDestination(exteriorDoors.get(p.getValue()).getComponent(PositionComponent.class).get());
            }

        }
        interiorMap.setBuilding(building);

        return new Result(true, "");
    }

    private static Result placeExterior(Entity entity, Vec2 position){
        GameMap activeMap = App.getActiveGame().getActiveMap();

        if(entity.getComponent(Placeable.class).getExteriorName() == null){
            return placeOnTile(entity, activeMap.getTileByPosition(position.getCol(), position.getRow()));
        }

        TileType[][] exterior = App.mapRegistry.getData(entity.getComponent(Placeable.class).getExteriorName()).getTypeMap();

        for(int i = 0 ; i < exterior.length ; i++){
            for(int j = 0 ; j < exterior[0].length; j++){
                Tile activeTile = activeMap.getTileByPosition(i + position.getRow(), j + position.getCol());
                TileType exteriorTile = exterior[i][j];

                if(exteriorTile != null){
                    activeTile.setType(exteriorTile);
                }
            }
        }

        activeMap.addEntity(entity);
        entity.removeComponent(PositionComponent.class);
        entity.addComponent(new PositionComponent(position, activeMap));

        return new Result(true, "");
    }

    public static boolean canPlace(int x, int y, Placeable placeable) {
        GameMap map = App.getActiveGame().getActiveMap();
        if (placeable.getExteriorName() == null) {
            Tile tile = map.getTileByPosition(y, x);
            return tile.getType().isWalkable && tile.getContent() == null ;
        }
        TileType[][] exterior = App.mapRegistry.getData(placeable.getExteriorName()).getTypeMap();

        for(int i = y; i < exterior.length; i++){
            for(int j = x; j < exterior[0].length; j++){
                if(exterior[i][j] != null){
                    Tile tile = App.getActiveGame().getMainMap().getTileByPosition(y, x);
                    if(tile == null || tile.getContent() != null) return false;
                }
            }
        }
        return true;
    }
    public static void clearArea(int x, int y, Placeable placeable) {
        GameMap map = App.getActiveGame().getActiveMap();

        TileType[][] exterior = App.mapRegistry.getData(placeable.getExteriorName()).getTypeMap();

        for(int i = y; i < exterior.length + y; i++){
            for(int j = x; j < exterior[0].length + x; j++){
                Tile tile = App.getActiveGame().getMainMap().getTileByPosition(j, i);
                if(tile.getContent() != null) tile.getContent().delete();
            }
        }
    }

}
