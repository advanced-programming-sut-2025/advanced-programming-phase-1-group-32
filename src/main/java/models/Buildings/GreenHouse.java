package src.main.java.models.Buildings;

import src.main.java.models.GameMap;
import src.main.java.models.Position;
import src.main.java.models.interfaces.Updatable;

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
