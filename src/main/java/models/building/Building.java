package models.building;

import models.App;
import models.entities.components.EntityComponent;
import models.entities.components.PositionComponent;
import models.entities.components.InteriorComponent;
import models.entities.components.PositionComponent;
import models.entities.systems.EntityPlacementSystem;
import models.gameMap.*;
import models.entities.Entity;
import models.entities.components.Placeable;
import models.gameMap.Environment;
import models.gameMap.GameMap;
import models.enums.TileType;
import models.Position;
import records.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Building extends Entity {
    private final Position position;
    private final Environment environment;

    public Building(BuildingData data, Position position) {
        super(data.name, new Placeable(App.mapRegistry.getData(data.exterior).getTypeMap()));
        for (EntityComponent c : data.components) {
            this.addComponent(c);
        }

        //TODO: should handle in above for, but :
        this.addComponent(new InteriorComponent(new GameMap(App.mapRegistry.getData(data.interiorMap), data.environment)));
        this.addComponent(new PositionComponent(position));

        this.width = data.width;
        this.height = data.height;
        this.environment = data.environment;
        this.position = position;

    }
}
