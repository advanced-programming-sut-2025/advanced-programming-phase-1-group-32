package models.shop;

import models.App;
import models.entities.Entity;
import models.entities.EntityRegistry;
import models.entities.components.Pickable;
import models.entities.components.inventory.Inventory;
import models.entities.components.inventory.InventorySlot;
import models.enums.Season;
import records.Result;

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

    public void addSold(int amount) {
        todaySold += amount;
    }

    public Entity getEntity() {
        return App.entityRegistry.makeEntity(name);
    }

    public boolean isInSeason(Season season) {
        if(this.season == null)
            return true;
        return this.season.equals(season);
    }

}
