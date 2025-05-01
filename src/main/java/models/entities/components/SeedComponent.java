package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.App;
import models.entities.Entity;

import java.util.ArrayList;

public class SeedComponent extends EntityComponent{
    @JsonProperty("growingPlants")
    private ArrayList<String> growingPlants = new ArrayList<>();

    public SeedComponent() {
    }

    public Entity getGrowingPlant(){
        int random = (int) (growingPlants.size() * Math.random());
        return App.entityRegistry.makeEntity(growingPlants.get(random));
    }

    @Override
    public EntityComponent clone() {
        return null;
    }
}
