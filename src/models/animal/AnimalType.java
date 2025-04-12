package models.animal;

import java.util.ArrayList;
import java.util.Map;

public enum AnimalType {
    Chicken (),
    Duck    (),
    Rabbit  (),
    Dinosaur(),
    Cow     (),
    Goat    (),
    Ship    (),
    Pig     ();

    private final ArrayList<AnimalProduct>    products;
    private final AnimalHouseType             animalHouseType;
    private final int                         cost;

    AnimalType(ArrayList<AnimalProduct> products, AnimalHouseType animalHouseType, int cost) {
        this.cost                = cost;
        this.products            = products;
        this.animalHouseType     = animalHouseType;
    }
}
