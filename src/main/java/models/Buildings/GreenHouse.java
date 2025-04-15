package models.Buildings;

import models.GameMap;
import models.Position;
import models.interfaces.Updatable;

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
