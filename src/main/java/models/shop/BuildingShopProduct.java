package models.shop;

import models.App;
import models.entities.Entity;

public class BuildingShopProduct extends ShopProduct {
    private int woodCost;
    private int stoneCost;

    public BuildingShopProduct(String name, int dailyLimit) {
        super(name, dailyLimit);
    }

    @Override
    public Entity getEntity() {
        return App.buildingRegistry.makeEntity(this.name);
    }

    public int getStoneCost() {
        return stoneCost;
    }

    public int getWoodCost() {
        return woodCost;
    }

}
