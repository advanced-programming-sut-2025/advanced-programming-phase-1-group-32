package models.shop;

import models.entities.Entity;

public class AnimalShopProduct extends ShopProduct {



    private String requiredBuildingName;



    public AnimalShopProduct(String name, int dailyLimit) {
        super(name, dailyLimit);
    }


    @Override
    public Entity getEntity() {
return null;
    }

    @Override
    public int getWoodCost() {
        return 0;
    }

    @Override
    public int getStoneCost() {
        return 0;
    }
}
