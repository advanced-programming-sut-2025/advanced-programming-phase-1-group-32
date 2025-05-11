package models.entities.components;

import models.App;
import models.building.Door;
import models.entities.Entity;
import models.entities.systems.EntityPlacementSystem;
import models.enums.TileType;
import models.gameMap.Environment;
import models.gameMap.GameMap;
import models.gameMap.Tile;

public class interiorComponent extends EntityComponent {

    private GameMap interior;
    private final String interiorMapName;
    private Environment environment;



    public interiorComponent(String interiorMapName, Environment environment) {

        GameMap worldMap = App.getActiveGame().getMainMap();

        this.interiorMapName = interiorMapName;
        this.environment = environment;
        this.interior = new GameMap(App.mapRegistry.getData(interiorMapName), environment);
        for (Tile[] t2 : interior.getTiles()) {
            for (Tile t : t2) {
                if(t.getType() == TileType.DOOR){
                    EntityPlacementSystem.placeOnTile(new Door(worldMap), t);
                }
            }
        }

    }


    @Override
    public EntityComponent clone() {
        return new interiorComponent(this.interiorMapName, this.environment);
    }
}
