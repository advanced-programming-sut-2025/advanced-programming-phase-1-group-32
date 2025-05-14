package models.entities.systems;

import models.App;
import models.entities.Entity;
import models.gameMap.BiomeType;
import models.gameMap.Tile;
import models.gameMap.WorldMap;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;

public class ForageSpawnSystem {
    public static void updatePerDay(){
        placeForageables();
    }
    public static void updatePerHour(){

    }
    public static void placeForageables(){
        SecureRandom random = new SecureRandom();
        WorldMap map = App.getActiveGame().getMainMap();
        Tile[][] tiles = map.getTiles();
        BiomeType[][] biomeMap = map.getBiomeMap();
        Map<BiomeType, ArrayList<BiomeType.Spawnable>> candidates = BiomeType.getCandidates(App.getActiveGame().getDate().getSeason());

        for(int i = 0 ; i < tiles.length; i++){
            for(int j = 0 ; j < tiles[0].length; j++){
                BiomeType biome = biomeMap[i][j];

                if(!EntityPlacementSystem.canPlace(tiles[i][j])) continue;

                if(random.nextFloat() > 0.02) continue;

                if(candidates.get(biome) == null) continue;

                Entity entity = App.entityRegistry.makeEntity(candidates.get(biome).get(random.nextInt(candidates.get(biome).size())).getEntity());

                EntityPlacementSystem.placeOnTile(entity, tiles[i][j]);
            }
        }
    }
}
