package models.gameMap;

public enum GameMapType {
    DEFAULT("src/data/maps/defaultMap.json");
    public final MapData data;

    GameMapType(String path) {
        this.data = MapData.parse(path);
    }
}