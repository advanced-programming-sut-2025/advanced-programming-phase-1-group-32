package models.animal;

import java.util.ArrayList;
import java.util.Arrays;

public enum AnimalType {
    Chicken(800,
            0,
            new ArrayList<>(Arrays.asList("Egg", "Big Egg")),
            AnimalHouseType.COOP,
            4,
            null),
    Duck(1200,
            1,
            new ArrayList<>(Arrays.asList("Duck Egg", "Duck Feather")),
            AnimalHouseType.COOP,
            8,
            null),
    Rabbit(8000,
            3,
            new ArrayList<>(Arrays.asList("Wool", "Rabbit Leg")),
            AnimalHouseType.COOP,
            12,
            null),
    Dinosaur(14000,
            6,
            new ArrayList<>(Arrays.asList("Dinosaur Egg")),
            AnimalHouseType.COOP,
            8,
            null),
    Cow(1500,
            0,
            new ArrayList<>(Arrays.asList("Cow Milk", "Cow Big Milk")),
            AnimalHouseType.BARN,
            4,
            "Milk pail"),
    Goat(4000,
        1,
            new ArrayList<>(Arrays.asList("Goat Milk", "Goat Big Milk")),
            AnimalHouseType.BARN,
            8,
            "Milk pail"),
    Sheep(8000,
        2,
            new ArrayList<>(Arrays.asList("Sheep Wool")),
            AnimalHouseType.BARN,
            12,
            ""),
    Pig(16000,
            0,
            new ArrayList<>(Arrays.asList("Truffle")),
            AnimalHouseType.BARN,
            12,
            null),;

    private final ArrayList<String> products;
    private final AnimalHouseType animalHouseType;
    private final int cost;
    private final int daysBetweenProduces;
    private final int neededCapacity;
    private final String neededTool;


    AnimalType(int cost, int daysBetweenProduces, ArrayList<String> products, AnimalHouseType animalHouseType, int neededCapacity, String neededTool) {
        this.cost = cost;
        this.daysBetweenProduces = daysBetweenProduces;
        this.products = products;
        this.animalHouseType = animalHouseType;
        this.neededCapacity = neededCapacity;
        this.neededTool = neededTool;
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

    public String getNeededTool() {
        return neededTool;
    }

    public static AnimalType getAnimalTypeByString(String animalTypeString) {
        for (AnimalType animalType : AnimalType.values()) {
            if (animalType.name().equalsIgnoreCase(animalTypeString)) {
                return animalType;
            }
        }
        return null;
    }
}
