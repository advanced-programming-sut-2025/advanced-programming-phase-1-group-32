package models.entities.components;

import models.animal.AnimalHouseType;

public class AnimalHouse extends EntityComponent{
    private AnimalHouseType type;

    @Override
    public EntityComponent clone() {
        return null;
    }
}
