package models.entities.components;

import models.App;
import models.building.Door;
import models.entities.systems.EntityPlacementSystem;
import models.enums.TileType;
import models.gameMap.GameMap;
import models.gameMap.MapRegistry;
import models.gameMap.Tile;

public class InteriorComponent extends EntityComponent {
    private GameMap interiorMap;
    private String interiorName;

    public InteriorComponent(String interiorName) {
        this.interiorName = interiorName;
    }
    public InteriorComponent(GameMap interiorMap) {
        this.interiorMap = interiorMap;
    }

    private InteriorComponent(InteriorComponent other) {
        this.interiorName = other.interiorName;

    }

    public GameMap getMap() {
        return this.interiorMap;
    }

    public String getInteriorName() {
        return interiorName;
    }

    public buildInterior(){
        this.interiorMap = new GameMap(App.mapRegistry.getData(interiorName))
    }

    @Override
    public EntityComponent clone() {
        return new InteriorComponent(this);
    }
}
