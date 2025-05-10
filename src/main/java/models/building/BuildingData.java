package models.building;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.App;
import models.Position;
import models.Tile;
import models.gameMap.Environment;

public class BuildingData {
    public static final BuildingData dummyBuilding = new BuildingData("dummy", "greenHouse", "greenHouseExterior", 7, 8, Environment.GREEN_HOUSE);

    @JsonProperty("name")
    public String name;
    @JsonProperty("interiorMap")
    public String interiorMap;
    @JsonProperty("exterior")
    public String exterior;
    @JsonProperty("width")
    public int width;
    @JsonProperty("height")
    public int height;
    @JsonProperty("environment")
    public Environment environment;

    public BuildingData(String name, String interiorMap, String exteriorMap, int width, int height, Environment environment) {
        this.name = name;
        this.interiorMap = interiorMap;
        this.width = width;
        this.height = height;
        this.environment = environment;
        this.exterior = exteriorMap;
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
