package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Edible extends EntityComponent{
    @JsonProperty("energy")
    private int energy;

    public Edible(int energy) {
        this.energy = energy;
    }

    public Edible() {
    }

    @Override
    public String toString() {
        return "Edible{" +
                "energy=" + energy +
                '}';
    }
}
