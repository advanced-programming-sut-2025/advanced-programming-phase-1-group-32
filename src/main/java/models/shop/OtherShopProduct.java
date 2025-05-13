package models.shop;

import models.App;
import models.entities.Entity;
import models.enums.Season;

public class OtherShopProduct extends ShopProduct{
    private Season season;

    public OtherShopProduct(String name, int dailyLimit) {
        super(name, dailyLimit);
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
}
