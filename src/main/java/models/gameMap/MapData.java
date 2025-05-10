package models.gameMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import models.enums.TileType;
import views.inGame.Color;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
class MapLayer{
    public String name;
    public int width;
    public int height;
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
    public MapLayer[] layers;
    public MapLayer mainLayer = null;
    public MapLayer regionsLayer = null;
    public ArrayList<TileSet> tileSets = new ArrayList<>();
    public TileSetReference[] tileSetReferences;
    public TileSet mainTileSet;
    public TileSet regionTileSet;
    public ArrayList<MapRegion> regions = new ArrayList<>();
    public Map<Integer, MapRegion> regionMap = new HashMap<>();
    public Map<Integer, TileType> typeMap = new HashMap<>();
    public Map<Integer, TileData> tileMap = new HashMap<>();

    @JsonCreator
    public MapData(@JsonProperty("layers") MapLayer[] layers, @JsonProperty("tilesets") TileSetReference[] tileSetReferences) {
        this.layers = layers;
        this.tileSets = tileSets;
        for(MapLayer l : layers){
            if(l.name.equals("ground")){
                this.mainLayer = l;
            } else if(l.name.equals("region")){
                regionsLayer = l;
            }
        }
        if(mainLayer == null){
            throw new RuntimeException("no layer with the name \"ground\" was found in the layers. the map needs a ground layer.");
        }

        XmlMapper mapper = new XmlMapper();

        for(TileSetReference t : tileSetReferences){
            try {
                TileSet tileset = mapper.readValue(new File(t.path.substring(t.path.lastIndexOf("../") + 3)), TileSet.class);
                tileset.firstgid = t.firstgid;
                this.tileSets.add(tileset);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        for(TileSet t : tileSets){
            if(t.name.equals("ground")){
                this.mainTileSet = t;
            }else if(t.name.equals("region")){
                this.regionTileSet = t;
            }
            for(TileData d : t.tiles){
                this.tileMap.putIfAbsent(t.firstgid + d.id, d);
                d.globalId = t.firstgid + d.id;
            }
        }
        if(mainTileSet == null){
            throw new RuntimeException("no tileset with the name \"ground\" was found in the tilesets. the map needs a ground tileset.");
        }

        if(regionsLayer != null && regionTileSet == null){
            throw new RuntimeException("the map has region layer, but no region tileset.");
        }

        for(TileData t : mainTileSet.tiles){
            try {
                TileType type = TileType.valueOf(t.type);
                typeMap.putIfAbsent(t.globalId, type);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("tile type \"" + t.type + "\" doesn't exist");
            }
        }
        if(regionsLayer != null){
            for(TileData t : regionTileSet.tiles){
                MapRegion region = new MapRegion(t.type, new Color(Math.random(), Math.random(), Math.random()));
                regions.add(region);
                regionMap.putIfAbsent(t.globalId, region);
            }
        }
        MapRegion defaultRegion = new MapRegion("Default", new Color(0, 0, 0));
        regions.add(defaultRegion);
        regionMap.putIfAbsent(0, defaultRegion);
    }
    public TileType[][] getTypeMap(){
        TileType[][] out = new TileType[mainLayer.height][mainLayer.width];

        for(int i = 0 ; i < mainLayer.height ; i++){
            for(int j = 0 ; j < mainLayer.width ; j++){
                out[i][j] = typeMap.get(mainLayer.data[i][j]);
            }
        }
        return out;
    }
    public MapRegion[][] getRegionMap(){
        MapRegion[][] out = new MapRegion[mainLayer.height][mainLayer.width];

        if(regionsLayer != null){
            for(int i = 0 ; i < mainLayer.height ; i++){
                for(int j = 0 ; j < mainLayer.width ; j++){
                    out[i][j] = regionMap.get(regionsLayer.data[i][j]);
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
@JacksonXmlRootElement(localName = "tile")
class TileData{
    @JacksonXmlProperty(isAttribute = true)
    public int id;
    @JacksonXmlProperty(isAttribute = true)
    public String type;
    public int globalId;
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "tileset")
class TileSet{
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "tile")
    public TileData[] tiles;
    public String name;
    public int firstgid;
}

class TileSetReference {
    @JsonProperty("firstgid")
    public int firstgid;
    @JsonProperty("source")
    public String path;
}


