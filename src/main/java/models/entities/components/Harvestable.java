package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.Result;
import models.entities.Entity;
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

    public Harvestable(@JsonProperty("resource") String resource, @JsonProperty("amount") int amount,
                       @JsonProperty("material")Material material) {
        this.resource = resource;
        this.amount = amount;
        this.material = material;
    }
}
