package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Edible extends EntityComponent{
    @JsonProperty("energy")
    private int energy;

    public Edible(@JsonProperty("energy")  int energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "Edible{" +
                "energy=" + energy +
                '}';
    }
}
