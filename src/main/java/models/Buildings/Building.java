package models.Buildings;

import models.GameMap;
import models.Position;

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
