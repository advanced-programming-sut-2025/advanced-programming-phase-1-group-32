package models.game.items.components;

import models.game.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends ItemComponent{

    private final ArrayList<Item> items = new ArrayList<>();
    private int capacity;

    public Inventory(int capacity){
        super(ItemComponentType.INVENTORY);
        this.capacity = capacity;
    }

    public int getItemCapacity() {
        return 0;
    }
    public void setItemCapacity() {

    }
    public boolean addItem() {
        return false;
    }
    public void removeItem() {

    }
    public boolean hasItem() {
        return false;
    }
    public List<Item> getItems() {
        return List.of();
    }
}
