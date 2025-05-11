package models.shop;

import models.App;
import models.entities.Entity;
import models.entities.EntityRegistry;
import models.enums.Season;

import javax.swing.*;

public class ShopProduct {
    protected String name;
    private Season season;
    protected int dailyLimit;
    protected int todaySold;
    private int price;

    private ShopProduct(){}

    protected ShopProduct(String name, int dailyLimit, int price) {
        this(name, null, dailyLimit, price);
    }

    public ShopProduct(String name, Season season, int dailyLimit, int price) {
        this.name = name;
        this.season = season;
        this.dailyLimit = dailyLimit;
        this.todaySold = 0;
        this.price = price;
    }

    public int getStock() {
        if(dailyLimit < 0) {
            return -1;
        }
        return dailyLimit - todaySold;
    }

    public Entity buy(int amount) {
        if(amount > getStock())
            return null;
        todaySold += amount;
        return App.entityRegistry.makeEntity(name);
    }

    public boolean isInSeason(Season season) {
        if(this.season == null)
            return true;
        return this.season.equals(season);
    }

}
