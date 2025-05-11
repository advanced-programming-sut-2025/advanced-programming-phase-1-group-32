package models.entities.components;

import models.App;
import models.building.Door;
import models.entities.systems.EntityPlacementSystem;
import models.enums.TileType;
import models.gameMap.Environment;
import models.gameMap.GameMap;
import models.gameMap.Tile;

public class InteriorComponent extends EntityComponent {

    private GameMap interior;



    public InteriorComponent(GameMap interior) {

        GameMap worldMap = App.getActiveGame().getMainMap();

        this.interior = interior;
        for (Tile[] t2 : interior.getTiles()) {
            for (Tile t : t2) {
                if(t.getType() == TileType.DOOR){
                    EntityPlacementSystem.placeOnTile(new Door(worldMap), t);
                }
            }
        }

    }

    public GameMap getMap() {
        return this.interior;
    }



    @Override
    public EntityComponent clone() {
        return new InteriorComponent(this.interior);
    }
}
