package models.entities.systems;

import models.App;
import models.Game;
import models.entities.Entity;
import models.entities.components.Growable;
import models.enums.Direction;
import models.enums.EntityTag;
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


//                int neighbours = 0;
//                for(Direction d : Direction.values()){
//                    Tile neighbour = map.getTileByPosition(i + d.dy, j + d.dx);
//                    if(neighbour == null) continue;
//
//                    Entity content = neighbour.getContent();
//                    if(content == null) continue;
//                    if(content.getComponent(Growable.class) != null || content.hasTag(EntityTag.FORAGING_CROP)){
//                        neighbours += 1;
//                    }
//                }

                if(!EntityPlacementSystem.canPlace(tiles[i][j])) continue;

                if(random.nextFloat() > 0.01) continue;

                if(candidates.get(biome) == null) continue;

                if(!candidates.get(biome).isEmpty()){
                    Entity entity = App.entityRegistry.makeEntity(candidates.get(biome).get(random.nextInt(candidates.get(biome).size())).getEntity());
                    EntityPlacementSystem.placeOnTile(entity, tiles[i][j]);
                }

            }
        }
    }

}
