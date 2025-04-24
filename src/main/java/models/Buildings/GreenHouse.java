package models.Buildings;

<<<<<<<< HEAD:src/models/game/Buildings/GreenHouse.java
import models.interfaces.Updatable;
========
import modelsMap;
import models.Position;
import models.interfaces.Updatable;
>>>>>>>> main:src/main/java/models/Buildings/GreenHouse.java

public class GreenHouse extends Building implements Updatable {

    @Override
    public void updatePerHour() {

    }

    @Override
    public void updatePerDay() {

    }

    public GreenHouse(Position position, int width, int height, GameMap map) {
        super(position, width, height, map);
    }
}
