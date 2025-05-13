package models.gameMap;

import models.App;
import models.Vec2;
import models.entities.Entity;
import models.entities.systems.EntityPlacementSystem;
import models.enums.TileType;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class WorldMap extends GameMap{
    private final ArrayList<MapRegion> regions = new ArrayList<>();
    private final MapRegion[][] regionMap;
    private final BiomeType[][] biomeMap;
    private final Map<MapRegion, FarmDetails> farmsDetail = new HashMap<>();

    public WorldMap(MapData data) {
        super(data, Environment.OUTDOOR);

        regionMap = data.getRegionMap();
        if(data.regions != null){
            this.regions.addAll(data.regions);
        }
        biomeMap = data.getBiomeMap();

        App.getActiveGame().setMainMap(this);
        App.getActiveGame().setActiveMap(this);

        for(MapRegion r : regions){
            farmsDetail.put(r, new FarmDetails());
        }

        for (MapData.MapLayerData<String>.ObjectData d : data.getBuildings()) {
            if(d.getProperty("type") != null && d.getProperty("type").asString.equals("playerHouse")){
                farmsDetail.get(regionMap[d.y][d.x]).cottage = d;
            }else{
                Entity building = App.entityRegistry.makeEntity(d.value);
                EntityPlacementSystem.placeEntity(building, new Vec2(d.x, d.y));
            }
        }

    }

    public MapRegion getRegion(int x, int y){
        if(x < 0 || x >= width || y < 0 || y > height){
            return null;
        }
        return regionMap[y][x];
    }
    public MapRegion getRegion(Tile tile){
        return getRegion(tile.getPosition().getCol(), tile.getPosition().getRow());
    }

    public ArrayList<MapRegion> getRegions() {
        return regions;
    }

    public void initRandomElements() {
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(Math.random() > 0.8){
                    BiomeType biome = biomeMap[i][j];
                    if(biome!=null && tiles[i][j].getContent() == null && tiles[i][j].getType() != TileType.WALL){
                        BiomeType.Spawnable spawnable = biome.spawnData.get(biome.spawnData.size()-1);

                        for (BiomeType.Spawnable s : biome.spawnData) {
                            if(Math.random() > s.weight / biome.totalWeight){
                                spawnable = s;
                            }
                        }

                        EntityPlacementSystem.placeOnTile(App.entityRegistry.makeEntity(spawnable.entity), tiles[i][j]);
                    }
                }
            }
        }
    }

    public Map<MapRegion, FarmDetails> getFarmsDetail() {
        return farmsDetail;
    }
}
