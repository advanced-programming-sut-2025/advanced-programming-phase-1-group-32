package models.shop;

import models.entities.Entity;
import models.enums.Season;

public class buildProduct extends ShopProduct{

    int woodCost, stoneCost;

    protected buildProduct(String name, int dailyLimit, int price, int woodCost, int stoneCost) {
        super(name, dailyLimit, price);
    }

    @Override
    public Entity buy(int amount) {

    }
}
