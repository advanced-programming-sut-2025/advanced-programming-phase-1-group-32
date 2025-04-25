package models.entities.components;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.entities.Entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory extends EntityComponent{
    @JsonProperty("items")
    @JsonIdentityReference(alwaysAsId = true)
    private final ArrayList<Entity> items;
    @JsonProperty("capacity")
    private int capacity;

    public Inventory(@JsonProperty("capacity") int capacity, @JsonProperty("items") ArrayList<Entity> items){
        this.items = items;
        this.capacity = capacity;
    }
    public Inventory(int capacity, Entity... items){
        this(capacity, new ArrayList<>(Arrays.asList(items)));
    }
    public Inventory(@JsonProperty("capacity") int capacity) {
        this(capacity, new ArrayList<>());
    }
}
