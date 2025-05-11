package models.building;

import models.App;
import models.entities.components.EntityComponent;
import models.entities.systems.EntityPlacementSystem;
import models.gameMap.Tile;
import models.entities.Entity;
import models.entities.components.Placeable;
import models.enums.TileType;
import models.gameMap.Environment;
import models.gameMap.GameMap;
import models.Position;
import records.Result;

public class Building extends Entity {
    private GameMap interior;
    private TileType[][] exterior;
    private final String interiorMapName;
    private final String exteriorMapName;
    private final Position position;
    private final int width, height;
    private final Environment environment;

    public Building(BuildingData data, Position position) {
        super(data.name);
        this.addComponent(new Placeable(false));
        for (EntityComponent c : data.components) {
            this.addComponent(c);
        }

        this.interiorMapName = data.interiorMap;
        this.width = data.width;
        this.height = data.height;
        this.environment = data.environment;
        this.position = position;
        this.exteriorMapName = data.exterior;

        this.interior = new GameMap(App.mapRegistry.getData(this.interiorMapName), environment);
        this.exterior = App.mapRegistry.getData(this.exteriorMapName).getTypeMap();

        GameMap worldMap = App.getActiveGame().getMainMap();

        for(int i = 0 ; i < exterior.length ; i++){
            for(int j = 0 ; j < exterior[0].length; j++){
                Tile worldTile = worldMap.getTileByPosition(j + position.getCol(), i + position.getRow());
                TileType exteriorTile = exterior[i][j];

                if(exteriorTile != null){
                    worldTile.setType(exteriorTile);
                }
                if(exterior[i][j] == TileType.DOOR){
                    EntityPlacementSystem.placeOnTile(new Door(this.interior), worldTile);
                }
            }
        }
        for (Tile[] t2 : interior.getTiles()) {
            for (Tile t : t2) {
                if(t.getType() == TileType.DOOR){
                    EntityPlacementSystem.placeOnTile(new Door(worldMap), t);
                }
            }
        }
        worldMap.addEntity(this);
    }
}
