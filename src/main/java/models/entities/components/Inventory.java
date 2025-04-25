package models.entities.components;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.entities.Entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory extends EntityComponent{
    @JsonProperty("items")
    @JsonIdentityReference(alwaysAsId = true)
    private final ArrayList<Entity> items = new ArrayList<>();
    @JsonProperty("capacity")
    private int capacity;

    public Inventory(int capacity, ArrayList<Entity> items){
        this.items.addAll(items);
        this.capacity = capacity;
    }
    public Inventory(int capacity, Entity... items){
        this(capacity, new ArrayList<>(Arrays.asList(items)));
    }
    public Inventory(int capacity) {
        this(capacity, new ArrayList<>());
    }
    public Inventory(){
    }
}
