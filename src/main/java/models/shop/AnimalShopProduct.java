package models.shop;

import models.animal.Animal;
import models.animal.AnimalType;
import models.entities.Entity;

public class AnimalShopProduct extends ShopProduct {

    private String requiredBuildingName;

    public AnimalShopProduct(String name, int dailyLimit) {
        super(name, dailyLimit);
    }

    @Override
    public Animal getEntity() {
        return new Animal(AnimalType.getAnimalTypeByString(this.name));
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
