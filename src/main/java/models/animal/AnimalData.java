package models.animal;

import models.entities.components.EntityComponent;

import java.util.ArrayList;

public class AnimalData {
    public String name;
    public AnimalHouseType houseType;
    public ArrayList<AnimalProduct> products = new ArrayList<>();
    public ArrayList<EntityComponent> entityComponents = new ArrayList<>();

    public AnimalData(String name, AnimalHouseType houseType, ArrayList<AnimalProduct> products, ArrayList<EntityComponent> entityComponents) {
        this.name = name;
        this.houseType = houseType;
        this.products = products;
        this.entityComponents = entityComponents;
    }
}
