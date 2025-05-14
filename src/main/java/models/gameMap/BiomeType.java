package models.gameMap;

import models.entities.systems.EntityPlacementSystem;
import models.enums.Season;

import java.util.*;

public enum BiomeType {
    FOREST(
            Map.of(
                Season.SPRING,
                Arrays.asList(
                    new Spawnable("Daffodil", 10)
                )
            ),
            Arrays.asList(
                new Spawnable("Pine Tree", 10),
                new Spawnable("Apricot Tree", 10)
            )
    ),
    MINE(
            Map.of(
            ),
            Arrays.asList(
                new Spawnable("Rock", 10)
            )
    ),
    TOWN(
            Map.of(
                Season.SPRING,
                Arrays.asList(

                )
            ),
            Arrays.asList(
                new Spawnable("Pine Tree", 10),
                new Spawnable("Apricot Tree", 10)
    ));

    public Map<Season, List<Spawnable>> seasonalForageables;
    public List<Spawnable> yearRoundForageables;

    public double totalWeight = 0;

    BiomeType(Map<Season, List<Spawnable>> seasonalForageables,
              List<Spawnable> yearRoundForageables) {
        this.seasonalForageables = seasonalForageables;
        this.yearRoundForageables = yearRoundForageables;
    }

    public static class Spawnable {
        String entity;
        double weight;

        public Spawnable(String entity, double weight) {
            this.entity = entity;
            this.weight = weight;
        }

        public String getEntity() {
            return entity;
        }

        public double getWeight() {
            return weight;
        }
    }

    public static Map<BiomeType, ArrayList<Spawnable>> getCandidates(Season season){
        Map<BiomeType, ArrayList<Spawnable>> result = new HashMap<>();
        for(BiomeType b : BiomeType.values()){
            ArrayList<Spawnable> candidates = new ArrayList<>(b.yearRoundForageables);
            if(b.seasonalForageables.get(season) != null){
                candidates.addAll(b.seasonalForageables.get(season));
            }
            result.put(b, candidates);
        }
        return result;
    }
}
