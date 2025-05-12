package models.building;

import models.App;
import models.entities.components.EntityComponent;
import models.entities.components.PositionComponent;
import models.entities.components.InteriorComponent;
import models.entities.Entity;
import models.entities.components.Placeable;
import models.gameMap.Environment;
import models.gameMap.GameMap;
import models.Position;
import records.Result;

public class Building extends Entity {
    private Environment environment;

    public Building(BuildingData data, Position position) {
        this(data);

        this.addComponent(new PositionComponent(position));

        this.environment = data.environment;




        App.getActiveGame().getMainMap().addEntity(this);
    }

    public Building(BuildingData data) {
        super(data.name, new Placeable(App.mapRegistry.getData(data.exterior).getTypeMap()));
        for (EntityComponent c : data.components) {
            this.addComponent(c);
        }
        //TODO: should handle in above for, but :
        this.addComponent(new InteriorComponent(new GameMap(App.mapRegistry.getData(data.interiorMap), data.environment)));

    }
}
