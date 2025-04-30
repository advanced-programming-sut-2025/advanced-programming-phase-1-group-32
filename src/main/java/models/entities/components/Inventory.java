package models.entities.components;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.Result;
import models.entities.Entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Inventory extends EntityComponent{
    @JsonProperty("slots")
    @JsonIdentityReference(alwaysAsId = true)
    private final ArrayList<Entity> entities;
    @JsonProperty("capacity")
    private int capacity;

    public Inventory(int capacity, ArrayList<Entity> entities){
        this.entities = new ArrayList<>(capacity);
        this.capacity = capacity;
    }
    public Inventory(int capacity, Entity... entities){
        this(capacity, new ArrayList<>(Arrays.asList(entities)));
    }
    public Inventory(int capacity) {
        this(capacity, new ArrayList<>());
    }
    public Inventory(){
        this(0);
    }

    public Result addItem(Entity entity){
        return this.addItem(entity, entity.getComponent(Pickable.class).getStackSize());
    }
    public Result addItem(Entity entity, int quantity){
        if(entity.getComponent(Pickable.class) == null){
            throw new RuntimeException("Tried to pick up " + entity.getName() + " which has no Pickable component");
        }
        boolean entityAdded = false;
        //add to existing stacks of the same item if possible
        for(Entity e : this.entities){
            if(e.equals(entity)){
                int leftSpace = e.getComponent(Pickable.class).getMaxStack() - e.getComponent(Pickable.class).getStackSize();
                int picked = Math.min(leftSpace, entity.getComponent(Pickable.class).getStackSize());
                e.getComponent(Pickable.class).changeStackSize(picked);
                quantity -= picked;
                if(entity.getComponent(Pickable.class).getStackSize() == 0){
                    entityAdded = true;
                    break;
                }
            }
        }
        if(entityAdded){
            return new Result(true, "");
        }
        //add to empty slots in inventory
        if(entity.getComponent(Pickable.class).getStackSize() != 0){
            for(Entity e : this.entities){
                if(e == null){
                    if(entity.getComponent(Pickable.class).getStackSize() > entity.getComponent(Pickable.class).getMaxStack()){
                        this.entities.add(entity.getComponent(Pickable.class).split(entity.getComponent(Pickable.class).getMaxStack()));
                    }else{
                        this.entities.add(entity);
                        entityAdded = true;
                    }
                }
            }
        }
        if(entityAdded){
            return new Result(true, "");
        }else{
            return new Result(false, "");
        }
    }
    public Result transferFromInventory(Entity entity, int quantity, Inventory source){
        this.addItem(entity, quantity);
        return null;
    }
    public ArrayList<Entity> getEntities(){
        return null;
    }
}
