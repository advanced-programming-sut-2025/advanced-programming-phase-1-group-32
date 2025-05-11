package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.entities.CollisionEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Placeable extends EntityComponent{
    @JsonProperty("isWalkable")
    private final boolean isWalkable;
    @JsonProperty("collisionFunctions")
    private ArrayList<CollisionEvent> collisionFunctions = new ArrayList<>();

    public Placeable(boolean isWalkable, CollisionEvent... collisionFunctions) {
        this.isWalkable = isWalkable;
        this.collisionFunctions.addAll(Arrays.asList(collisionFunctions));
    }
    private Placeable(Placeable other){
        this.isWalkable = other.isWalkable;
        this.collisionFunctions.addAll(other.collisionFunctions);
    }
    public Placeable(){
        this(false);
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    public ArrayList<CollisionEvent> getCollisionEvents() {
        return collisionFunctions;
    }

    @Override
    public String toString() {
        return "Placeable{" +
                "isWalkable=" + isWalkable +
                '}';
    }

    @Override
    public EntityComponent clone() {
        return new Placeable(this);
    }
}
