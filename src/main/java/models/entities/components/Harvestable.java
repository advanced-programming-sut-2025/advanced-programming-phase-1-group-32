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

    public Result canHarvest(){
        return null;
    }
    public Result harvest(){
        return null;
    }

    public Harvestable(String resource, int amount, Material material) {
        this.resource = resource;
        this.amount = amount;
        this.material = material;
    }

    public Harvestable() {
        this.material = null;
        this.resource = null;
    }

    @Override
    public String toString() {
        return "Harvestable{" +
                "material=" + material +
                ", resource='" + resource + '\'' +
                ", amount=" + amount +
                '}';
    }
}
