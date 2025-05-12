package models.entities.components;

import models.App;
import models.building.Door;
import models.entities.systems.EntityPlacementSystem;
import models.enums.TileType;
import models.gameMap.GameMap;
import models.gameMap.Tile;

public class InteriorComponent extends EntityComponent {

    private GameMap interiorMap;



    public InteriorComponent(GameMap interiorMap) {

        GameMap worldMap = App.getActiveGame().getMainMap();

        this.interiorMap = interiorMap;
        for (Tile[] t2 : interiorMap.getTiles()) {
            for (Tile t : t2) {
                if(t.getType() == TileType.DOOR){
                    EntityPlacementSystem.placeOnTile(new Door(worldMap), t);
                }
            }
        }

    }

    private InteriorComponent(InteriorComponent other) {
        this.interiorMap = other.interiorMap;
    }

    public GameMap getMap() {
        return this.interiorMap;
    }



    @Override
    public EntityComponent clone() {
        return new InteriorComponent(this);
    }
}
