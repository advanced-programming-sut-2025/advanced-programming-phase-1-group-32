package models.animal;

import java.util.ArrayList;
import java.util.Arrays;

public enum AnimalType {
    Chicken(800,
            0,
            new ArrayList<>(Arrays.asList("Egg", "Big Egg")),
            AnimalHouseType.COOP,
            4),
    Duck(1200,
            1,
            new ArrayList<>(Arrays.asList("Duck Egg", "Duck Feather")),
            AnimalHouseType.COOP,
            8),
    Rabbit(8000,
            3,
            new ArrayList<>(Arrays.asList("Wool", "Rabbit Leg")),
            AnimalHouseType.COOP,
            12),
    Dinosaur(14000,
            6,
            new ArrayList<>(Arrays.asList("Dinosaur Egg")),
            AnimalHouseType.COOP,
            8),
    Cow(1500,
            0,
            new ArrayList<>(Arrays.asList("Cow Milk", "Cow Big Milk")),
            AnimalHouseType.BARN,
            4),
    Goat(4000,
        1,
            new ArrayList<>(Arrays.asList("Goat Milk", "Goat Big Milk")),
            AnimalHouseType.BARN,
            8),
    Ship(8000,
        2,
            new ArrayList<>(Arrays.asList("Ship Wool")),
            AnimalHouseType.BARN,
            12),
    Pig(16000,
            0,
            new ArrayList<>(Arrays.asList("Truffle")),
            AnimalHouseType.BARN,
            12);

    private final ArrayList<String> products;
    private final AnimalHouseType animalHouseType;
    private final int cost;
    private final int daysBetweenProduces;
    private final int neededCapacity;


    AnimalType(int cost, int daysBetweenProduces, ArrayList<String> products, AnimalHouseType animalHouseType, int neededCapacity) {
        this.cost = cost;
        this.daysBetweenProduces = daysBetweenProduces;
        this.products = products;
        this.animalHouseType = animalHouseType;
        this.neededCapacity = neededCapacity;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public AnimalHouseType getAnimalHouseType() {
        return animalHouseType;
    }

    public int getCost() {
        return cost;
    }

    public int getDaysBetweenProduces() {
        return daysBetweenProduces;
    }

    public int getNeededCapacity() {
        return neededCapacity;
    }
}
