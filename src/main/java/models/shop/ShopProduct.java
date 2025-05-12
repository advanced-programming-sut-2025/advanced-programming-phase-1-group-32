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
import java.util.HashMap;

public class ShopProduct {
    protected String name;
    private Season season;
    protected int dailyLimit;
    protected int todaySold;
    private int price;
    private int woodCost;
    private int stoneCost;
    /*
    * costs should be like this:
    *   "price" : 100,
    *   "wood" : 200,
    *   "stone" : 100
    *
    *
    *
    * */

    private ShopProduct(){}

    protected ShopProduct(String name, int dailyLimit) {
        this(name, null, dailyLimit);
    }

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


    public int getPrice() {
        return this.price;
    }

    public int getStoneCost() {
        return stoneCost;
    }

    public int getWoodCost() {
        return woodCost;
    }
}
