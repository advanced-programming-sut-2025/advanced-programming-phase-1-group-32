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

    private Edible(Edible other){
        this.energy = other.energy;
    }

    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "Edible{" +
                "energy=" + energy +
                '}';
    }



    @Override
    public EntityComponent clone() {
        return new Edible(this);
    }
}
