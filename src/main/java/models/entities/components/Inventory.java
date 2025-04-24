package models.entities.components;

import models.entities.Entity;

import java.util.ArrayList;

public class Inventory extends EntityComponent{
    private final ArrayList<Entity> items = new ArrayList<>();
    private int capacity;

    public Inventory(int capacity) {
        this.capacity = capacity;
    }
}
