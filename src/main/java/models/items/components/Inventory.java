package models.items.components;

import models.items.Entity;

import java.util.ArrayList;

public class Inventory extends EntityComponent{
    private final ArrayList<Entity> entities = new ArrayList<>();
    int capacity;
}
