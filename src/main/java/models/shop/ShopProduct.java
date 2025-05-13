package models.shop;

import com.fasterxml.jackson.annotation.JsonSubTypes;
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

/*
public interface ShopProduct {

    int getStock();
    int getPrice();
    int getWoodCost();
    int getStoneCost();
    Entity getEntity();

    void addSold(int amount);

}
*/


@JsonSubTypes({
        @JsonSubTypes.Type(value = AnimalShopProduct.class, name = "Animal"),
        @JsonSubTypes.Type(value = BuildingShopProduct.class, name = "Building"),
        @JsonSubTypes.Type(value = OtherShopProduct.class, name = "Product")
})
abstract public class ShopProduct {
    protected String name;
    protected int dailyLimit;
    protected int todaySold;
    protected int price;
    /*
    * costs should be like this:
    *   "price" : 100,
    *   "wood" : 200,
    *   "stone" : 100
    *
    *
    *
    *
    * */

    private ShopProduct() {

    }

    public ShopProduct(String name, int dailyLimit) {
        this.name = name;
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

    public int getPrice() {
        return this.price;
    }

    abstract public Entity getEntity();
    abstract public int getWoodCost();
    abstract public int getStoneCost();

}