package src.main.java.models.Buildings;

import src.main.java.models.GameMap;
import src.main.java.models.Position;

abstract public class Building {
    protected final Position position;
    protected final int width, height;
    protected final GameMap map;

    public Building(Position position, int width, int height, GameMap map) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.map = map;
    }


}
