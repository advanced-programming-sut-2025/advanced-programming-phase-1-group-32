package models.building;

import models.App;
import models.Tile;
import models.entities.Entity;
import models.entities.components.EntityComponent;
import models.entities.components.Placeable;
import models.entities.components.Renderable;
import models.enums.EntityTag;
import models.enums.TileType;
import models.gameMap.Environment;
import models.gameMap.GameMap;
import models.Position;
import views.inGame.Color;
import views.inGame.Renderer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Building extends Entity implements Serializable {
    private GameMap interior;
    private TileType[][] exterior;
    private final String interiorMapName;
    private final String exteriorMapName;
    private final Position position;
    private final int width, height;
    private final Environment environment;

    public Building() {
        super("", null, null, 0);
        this.interiorMapName = "";
        this.exteriorMapName = "";
        this.position = null;
        this.width = 0;
        this.height = 0;
        this.environment = null;
    }

    public Building(BuildingData data, Position position) {
        super("BUILDING", new Placeable(false));
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
            }
        }

        worldMap.addEntity(this);
    }
}
