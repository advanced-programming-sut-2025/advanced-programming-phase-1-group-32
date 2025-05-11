package models.gameMap;

import java.util.ArrayList;
import java.util.Arrays;

public enum BiomeType {
    FOREST(
            new Spawnable("Pine Tree", 10),
            new Spawnable("Apricot Tree", 10)
    ),
    MINE(
            new Spawnable("Rock", 10)
    ),
    TOWN();

    public ArrayList<Spawnable> spawnData = new ArrayList<>();
    public double totalWeight = 0;

    BiomeType(Spawnable... spawnables) {
        for (Spawnable s : spawnables) {
            this.spawnData.add(s);
            totalWeight += s.weight;
        }
    }

    public static class Spawnable{
        String entity;
        double weight;

        public Spawnable(String entity, double weight) {
            this.entity = entity;
            this.weight = weight;
        }
    }
}
