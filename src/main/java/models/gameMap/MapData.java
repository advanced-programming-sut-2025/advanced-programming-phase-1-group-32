package models.gameMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.json.JsonMapper;
import models.enums.TileType;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
class MapLayer{
    @JsonProperty("name")
    public String name;
    @JsonProperty("width")
    public int width;
    @JsonProperty("height")
    public int height;
    @JsonProperty("data")
    private final int[] data1d;
    public final int[][] data;

    @JsonCreator
    public MapLayer(@JsonProperty("name")String name,@JsonProperty("width") int width,@JsonProperty("height") int height,@JsonProperty("data") int[] data) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.data1d = data;

        this.data = new int[height][width];
        for(int i = 0 ; i < height ; i++){
            for(int j = 0 ; j < width ; j++){
                this.data[i][j] = data1d[i * width + j];
            }
        }
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
public class MapData{
    @JsonProperty("layers")
    public MapLayer[] layers;
    public MapLayer mainLayer = null;
    @JsonProperty("tilesets")
    public TileSet[] tileSets;
    public TileSet mainTileSet;

    @JsonCreator
    public MapData(@JsonProperty("layers") MapLayer[] layers, @JsonProperty("tilesets") TileSet[] tileSets) {
        this.layers = layers;
        this.tileSets = tileSets;
        for(MapLayer l : layers){
            if(l.name.equals("ground")){
                this.mainLayer = l;
                break;
            }
        }
        if(mainLayer == null){
            throw new RuntimeException("no layer with the name \"ground\" was found in the layers. the map needs a ground layer.");
        }
        for(TileSet t : tileSets){
            if(t.name.equals("ground")){
                this.mainTileSet = t;
                break;
            }
        }
        if(mainTileSet == null){
            throw new RuntimeException("no tileset with the name \"ground\" was found in the tilesets. the map needs a ground tileset.");
        }
    }

    public TileType[][] getTypeMap(){
        TileType[][] out = new TileType[mainLayer.height][mainLayer.width];

        for(int i = 0 ; i < mainLayer.height ; i++){
            for(int j = 0 ; j < mainLayer.width ; j++){
                out[i][j] = mainTileSet.tileTypes.get(mainLayer.data[i][j]);
                if(out[i][j] == null){
                    throw new RuntimeException("null");
                }
            }
        }
        return out;
    }
    public static MapData parse(String path){
        Path file = Paths.get(path);

        JsonMapper mapper = new JsonMapper();
        MapData data;
        try {
            data = mapper.treeToValue(mapper.readTree(file.toFile()), MapData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class TileData{
    @JsonProperty("id")
    public int id;
    @JsonProperty("type")
    public String type;

    public TileData() {
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class TileSet{
    @JsonProperty("tiles")
    private TileData[] tiles;
    public Map<Integer, TileType> tileTypes = new HashMap<>();
    @JsonProperty("name")
    public String name;

    @JsonCreator
    public TileSet(@JsonProperty("tiles")TileData[] tiles, @JsonProperty("name")String name) {
        this.tiles = tiles;
        this.name = name;

        for(TileData t : tiles){
            try {
                TileType type = TileType.valueOf(t.type);
                tileTypes.putIfAbsent(t.id + 1, type);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("tile type \"" + t.type + "\" doesn't exist");
            }
        }
    }
}


