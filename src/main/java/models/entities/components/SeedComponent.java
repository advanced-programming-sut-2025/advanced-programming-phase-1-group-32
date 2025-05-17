package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.App;
import models.entities.Entity;
import models.enums.Season;

import java.util.ArrayList;

public class SeedComponent extends EntityComponent{
    @JsonProperty("growingPlants")
    private ArrayList<String> growingPlants = new ArrayList<>();
    @JsonProperty("canBeForaging")
    private boolean canBeForaging;
    @JsonProperty("foragingSeasons")
    private ArrayList<Season> foragingSeasons = new ArrayList<>();

    public SeedComponent() {
    }

    public SeedComponent(SeedComponent other) {
        this.growingPlants.addAll(other.growingPlants);
    }

    public Entity getGrowingPlant(){
        int random = (int) (growingPlants.size() * Math.random());
        return App.entityRegistry.makeEntity(growingPlants.get(random));
    }

    @Override
    public EntityComponent clone() {
        return new SeedComponent(this);
    }
}
