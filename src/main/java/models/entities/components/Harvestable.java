package models.entities.components;

import models.Result;
import models.entities.Entity;
import models.enums.Material;

public class Harvestable {
    private final Material material;
    private final Entity resource;
    private int amount;

    public Result canHarvest(){
        return null;
    }
    public Result harvest(){
        return null;
    }

    public Harvestable(Entity resource, int amount, Material material) {
        this.resource = resource;
        this.amount = amount;
        this.material = material;
    }
}
