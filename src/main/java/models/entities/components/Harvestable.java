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
    @JsonProperty("regrowthTime")
    private int regrowthTime;
    @JsonProperty("oneTime")
    private boolean oneTime;


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

    @Override
    public EntityComponent clone() {
        return new Harvestable(this);
    }

    public Material getMaterial() {
        return material;
    }

    public String getResource() {
        return resource;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public void setOneTime(boolean oneTime) {
        this.oneTime = oneTime;
    }

    public int getRegrowthTime() {
        return regrowthTime;
    }

    public void setRegrowthTime(int regrowthTime) {
        this.regrowthTime = regrowthTime;
    }
}
