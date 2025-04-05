package models.items;

import models.enums.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BackPack extends Item implements Inventory{
    private final ArrayList<Item> items = new ArrayList<>();
    private int capacity;
    @Override
    public int getItemCapacity() {
        return 0;
    }

    @Override
    public void setItemCapacity() {

    }

    @Override
    public boolean addItem() {
        return false;
    }

    @Override
    public void removeItem() {

    }

    @Override
    public boolean hasItem() {
        return false;
    }

    @Override
    public List<Item> getItems() {
        return List.of();
    }
}
