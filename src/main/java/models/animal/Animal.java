package models.animal;

import models.App;
import models.Position;
import models.entities.Entity;
import models.entities.components.EntityComponent;
import models.entities.components.Renderable;
import models.entities.components.Sellable;
import models.enums.EntityTag;
import models.enums.ProductQuality;
import models.interfaces.Updatable;
import models.player.Player;
import models.player.friendship.AnimalFriendship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public  class Animal extends Entity {
    private AnimalType animalType;
    private String name;
    private ArrayList<String> produces;
    private Entity todayProduct;
    private int daysBetweenProduces;
    private int daysPastLastProduce;

    // friendship
    private boolean isPetToday = false;
    private boolean isFedToday = false;
    private int friendshipLevel = 0;

    public Animal(String name, ArrayList<EntityComponent> components, HashSet<EntityTag> tags, int id) {
        super(name);
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

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

    public void addFriendshipLevel(int amount) {
        this.friendshipLevel += amount;
        if (this.friendshipLevel > 1000) {
            this.friendshipLevel = 1000;
        }

    }

    public void reduceFriendshipLevel(int amount) {
        this.friendshipLevel -= amount;
        if (this.friendshipLevel < 0) {
            this.friendshipLevel = 0;
        }
    }

    public void updatePerDay() {
        setupTodayProduct();

        if (!isFedToday) {
            reduceFriendshipLevel(20);
        }
        isFedToday = false;

        if (!isPetToday) {
            reduceFriendshipLevel(10);
        }
        isPetToday = false;

        //TODO: reduce if its out of house
    }

    public void setupTodayProduct() {
        Entity todayProduct = null;
        if (daysPastLastProduce < daysBetweenProduces) {
            daysPastLastProduce++;
            return;
        }

        if (isFedToday) {
            if (friendshipLevel > 100) {
                double rand = Math.random() + 0.5;
                double probability = (friendshipLevel + 150 * rand) / 1500;
                if (Math.random() < probability) {
                    todayProduct = produceProduct(1);
                } else {
                    todayProduct = produceProduct(0);
                }
            } else {
                todayProduct = produceProduct(0);
            }

        }

        setTodayProduct(todayProduct);
    }

    public Entity produceProduct(int whichOne) {
        double quality = ((double) friendshipLevel / 1000) * (0.5 + 0.5 * Math.random());
        Entity product = App.entityRegistry.makeEntity(produces.get(whichOne));
        product.getComponent(Sellable.class).setProductQuality(ProductQuality.getQuality(quality));

        return product;
    }

    public String getDetail() {
        StringBuilder result = new StringBuilder();
        result.append("Type:" ).append(animalType).append("\n");
        result.append("Name:").append(name).append("\n");
        result.append("IsPetToday:").append(isPetToday).append("\n");
        result.append("IsFedToday:").append(isFedToday).append("\n");
        result.append("FriendshipLevel:").append(friendshipLevel).append("\n");
        result.append("---------------------------------------------------------------\n");

        return result.toString();
    }
}
