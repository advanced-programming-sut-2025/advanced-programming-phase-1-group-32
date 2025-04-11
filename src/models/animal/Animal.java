package models.animal;

import models.items.Item;
import models.player.Player;
import models.player.friendship.AnimalFriendship;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Animal {
    private int cost;
    private ArrayList<Item> products;
    private final AnimalHouseType animalHouseType;

    public void pet() {

    }


    private HashMap<Player, AnimalFriendship> friendshipHashMap;
}
