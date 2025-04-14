package src.main.java.models.items;

import src.main.java.models.Position;
import src.main.java.models.interfaces.Inventory;

import java.util.ArrayList;
import java.util.List;

public class BackPack extends Item implements Inventory {
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

    @Override
    public void useItem() {

    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition() {

    }

    @Override
    public void placeOnGround() {

    }
}
