package models.building;

import models.App;
import models.entities.components.EntityComponent;
import models.entities.components.interiorComponent;
import models.entities.systems.EntityPlacementSystem;
import models.gameMap.Tile;
import models.entities.Entity;
import models.entities.components.Placeable;
import models.enums.TileType;
import models.gameMap.Environment;
import models.gameMap.GameMap;
import models.Position;

public class Building extends Entity {
    private final Position position;
    private final int width, height;
    private final Environment environment;

    public Building(BuildingData data, Position position) {
        super(data.name, new Placeable(App.mapRegistry.getData(data.exterior).getTypeMap(), data.exterior));
        for (EntityComponent c : data.components) {
            this.addComponent(c);
        }

        //TODO: should handle in above for, but :
        this.addComponent(new interiorComponent(data.interiorMap, data.environment));


        this.width = data.width;
        this.height = data.height;
        this.environment = data.environment;
        this.position = position;




        App.getActiveGame().getMainMap().addEntity(this);


    }
}
