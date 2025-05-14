package models.shop;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.App;
import models.entities.Entity;

public class UpgradableShopProduct extends ShopProduct {

    String ingredient;
    int count = 5;
    @JsonCreator
    public UpgradableShopProduct(@JsonProperty("name") String name,
                                 @JsonProperty("dailyLimit") int dailyLimit,
                                 @JsonProperty("price") int price,
                                 @JsonProperty("ingredient") String ingredient,
                                 @JsonProperty("count") int count
    ) {
        super(name, dailyLimit, price);
        this.ingredient = ingredient;
        this.count = count;
    }

    @Override
    public Entity getEntity() {
        return App.entityRegistry.makeEntity(this.name);
    }

    @Override
    public int getWoodCost() {
        return 0;
    }

    @Override
    public int getStoneCost() {
        return 0;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
