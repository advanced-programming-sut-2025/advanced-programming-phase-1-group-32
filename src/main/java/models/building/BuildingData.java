package models.building;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.App;
import models.Position;
import models.entities.components.EntityComponent;
import models.gameMap.GameMap;
import models.gameMap.MapData;
import models.gameMap.Tile;
import models.gameMap.Environment;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildingData {
    public static final BuildingData dummyBuilding = new BuildingData("dummy", "greenHouse", "greenHouseExterior", Environment.GREEN_HOUSE);

    @JsonProperty("name")
    public String name;
    @JsonProperty("interior")
    public String interiorMap;
    @JsonProperty("exterior")
    public String exterior;
    public int width;
    public int height;
    @JsonProperty("environment")
    public Environment environment;
    @JsonProperty("components")
    public ArrayList<EntityComponent> components = new ArrayList<>();

    public BuildingData(String name, String interiorMap, String exteriorMap, Environment environment,
                        EntityComponent... components) {
        this.name = name;
        this.interiorMap = interiorMap;
        this.environment = environment;
        this.exterior = exteriorMap;
        this.components.addAll(Arrays.asList(components));
        MapData mapData = App.mapRegistry.getData(exteriorMap);


        this.width = mapData.width;
        this.height = mapData.height;
    }

    public BuildingData() {
    }

    public boolean canPlace(int x, int y){
        for(int i = y; i < height; i++){
            for(int j = x; j < width; j++){
                Tile tile = App.getActiveGame().getMainMap().getTileByPosition(x, y);

                if(tile == null || tile.getContent() != null) return false;
            }
        }
        return true;
    }
    public boolean canPlace(Position position){
        return canPlace(position.getCol(), position.getRow());
    }

    public boolean canPlace(Tile tile){
        return canPlace(tile.getPosition());
    }

    public void clearArea(int x, int y){
        for(int i = y; i < height + y; i++){
            for(int j = x; j < width + x; j++){
                Tile tile = App.getActiveGame().getMainMap().getTileByPosition(j, i);
                if(tile.getContent() != null) tile.getContent().delete();
            }
        }
    }
}
