package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import records.Result;
import models.enums.Material;

public class Harvestable extends EntityComponent{
    @JsonProperty("material")
    private final Material material;
    @JsonProperty("resource")
    private final String resource;
    @JsonProperty("amount")
    private int amount;


    public Harvestable(String resource, int amount, Material material) {
        this.resource = resource;
        this.amount = amount;
        this.material = material;
    }
    private Harvestable(Harvestable other){
        this.resource = other.resource;
        this.material = other.material;
        this.amount = other.amount;
    }
    public Harvestable() {
        this.material = null;
        this.resource = null;
    }

    public Result canHarvest(){
        return null;
    }
    public Result harvest(){
        //TODO
        return null;
    }

    @Override
    public String toString() {
        return "Harvestable{" +
                "material=" + material +
                ", resource='" + resource + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public EntityComponent clone() {
        return new Harvestable(this);
    }
}
