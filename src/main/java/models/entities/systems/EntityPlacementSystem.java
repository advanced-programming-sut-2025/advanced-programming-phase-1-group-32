package models.entities.systems;

import models.Position;
import models.entities.Entity;
import models.entities.components.InteriorComponent;
import models.entities.components.Placeable;
import models.entities.components.PositionComponent;
import models.gameMap.GameMap;
import models.gameMap.Tile;
import records.Result;

import javax.management.ImmutableDescriptor;

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

    public static Result placeEntity(Entity entity) {
        Placeable placeable = entity.getComponent(Placeable.class);
        InteriorComponent interiorComponent = entity.getComponent(InteriorComponent.class);
        if(placeable == null)
            return new Result(false, "This entity isn't placeable");
        if(interiorComponent == null)
            return placeable.place(entity.getComponent(PositionComponent.class));
        return placeable.place(entity.getComponent(PositionComponent.class), interiorComponent.getMap());
    }
}
