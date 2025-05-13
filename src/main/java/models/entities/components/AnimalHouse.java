package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.animal.Animal;
import models.animal.AnimalHouseLevel;
import models.animal.AnimalHouseType;
import models.entities.Entity;

import java.util.ArrayList;

public class AnimalHouse extends EntityComponent{
    @JsonProperty("houseType")
    private AnimalHouseType type;
    @JsonProperty("capacity")
    private int capacity;
    private ArrayList<Animal> animals;

    public AnimalHouse(AnimalHouseType type, int capacity) {
        this.type = type;
        this.capacity = capacity;
    }

    public AnimalHouse() {
    }

    @Override
    public EntityComponent clone() {
        return null;
    }
}
