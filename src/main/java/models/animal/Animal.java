package models.animal;

import models.Position;
import models.entities.Entity;
import models.entities.components.Renderable;
import models.interfaces.Updatable;
import models.player.Player;
import models.player.friendship.AnimalFriendship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public  class Animal  {
    private String name;
    private ArrayList<String> produces;
    private Entity todayProduct;
    private int daysBetweenProduces;
    private int daysPastLastProduce;

    // friendship
    private boolean isPetToday;
    private boolean isFedToday;
    private int friendshipLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getProduces() {
        return produces;
    }

    public void setProduces(ArrayList<String> produces) {
        this.produces = produces;
    }

    public Entity getTodayProduct() {
        return todayProduct;
    }

    public void setTodayProduct(Entity todayProduct) {
        this.todayProduct = todayProduct;
    }

    public int getDaysBetweenProduces() {
        return daysBetweenProduces;
    }

    public void setDaysBetweenProduces(int daysBetweenProduces) {
        this.daysBetweenProduces = daysBetweenProduces;
    }

    public int getDaysPastLastProduce() {
        return daysPastLastProduce;
    }

    public void setDaysPastLastProduce(int daysPastLastProduce) {
        this.daysPastLastProduce = daysPastLastProduce;
    }

    public boolean isPetToday() {
        return isPetToday;
    }

    public void setPetToday(boolean petToday) {
        isPetToday = petToday;
    }

    public boolean isFedToday() {
        return isFedToday;
    }

    public void setFedToday(boolean fedToday) {
        isFedToday = fedToday;
    }

    public int getFriendshipLevel() {
        return friendshipLevel;
    }

    public void setFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel = friendshipLevel;
    }
}
