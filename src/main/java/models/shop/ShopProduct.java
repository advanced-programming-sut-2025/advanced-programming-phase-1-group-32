package models.shop;

import models.App;
import models.entities.Entity;
import models.entities.EntityRegistry;
import models.enums.Season;

import javax.swing.*;

public class ShopProduct {
    private String name;
    private Season season;
    private int dailyLimit;
    private int todaySold;

    private ShopProduct(){}

    public ShopProduct(String name, Season season, int dailyLimit) {
        this.name = name;
        this.season = season;
        this.dailyLimit = dailyLimit;
        this.todaySold = 0;
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
