package models.interfaces;

import models.items.Item;

import java.util.List;

public interface Inventory {
    public int getItemCapacity();

    public void setItemCapacity();

    public boolean addItem();

    public void removeItem();

    public boolean hasItem();

    public List<Item> getItems();
}
